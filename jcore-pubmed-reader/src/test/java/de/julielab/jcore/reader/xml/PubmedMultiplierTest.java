package de.julielab.jcore.reader.xml;

import de.julielab.jcore.utility.JCoReTools;
import org.apache.uima.UIMAException;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.collection.metadata.CpeDescriptorException;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;
import org.apache.uima.jcas.JCas;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests the multiplier reader together with the multiplier in a simple CPE.
 */
public class PubmedMultiplierTest {

    /**
     * To access the contents created in the {@link TestAe}, we create this static field and let the AE fill it.
     */
    private static List<String> seenPmids = new ArrayList<>();

    @Test
    public void testMultiplier() throws IOException, UIMAException, CpeDescriptorException, SAXException {
        CollectionReaderDescription reader = CollectionReaderFactory.createReaderDescriptionFromPath(
                "src/main/resources/de/julielab/jcore/reader/xml/desc/jcore-pubmed-multiplier-reader.xml",
                XMLMultiplierReader.PARAM_INPUT_DIR, "src/test/resources/pubmedArticleSets");
        AnalysisEngineDescription multiplier = AnalysisEngineFactory.createEngineDescriptionFromPath(
                "src/main/resources/de/julielab/jcore/reader/xml/desc/jcore-pubmed-articleset-multiplier.xml");
        AnalysisEngineDescription testAe = AnalysisEngineFactory.createEngineDescription(TestAe.class);
        AnalysisEngineDescription topAAE = AnalysisEngineFactory.createEngineDescription(Arrays.asList(multiplier, testAe), Arrays.asList("Multiplier", "TestAe"), null, null, null);

        SimplePipeline.runPipeline(reader, topAAE);

        // The seenPmids list is filled by the TestAe (see code below).
        assertEquals(177, seenPmids.size());
        for (String pmid : seenPmids)
            assertTrue(pmid.matches("[0-9]+"));


    }

    public static class TestAe extends JCasAnnotator_ImplBase {

        @Override
        public void process(JCas aJCas) throws AnalysisEngineProcessException {
            String docId = JCoReTools.getDocId(aJCas);
            // Write PMIDs into the list. The samples often don't have
            // abstracts or even titles.
            seenPmids.add(docId);
        }
    }
}

