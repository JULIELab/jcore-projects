package de.julielab.jcore.reader.xml;

import de.julielab.jcore.types.pubmed.Header;
import de.julielab.jcore.types.pubmed.OtherID;
import de.julielab.jcore.utility.JCoReTools;
import org.apache.uima.UIMAException;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.collection.CollectionProcessingEngine;
import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.collection.EntityProcessStatus;
import org.apache.uima.collection.StatusCallbackListener;
import org.apache.uima.collection.metadata.CpeDescriptorException;
import org.apache.uima.fit.cpe.CpeBuilder;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        AnalysisEngineDescription topTopAAE = AnalysisEngineFactory.createEngineDescription(Arrays.asList(topAAE), Arrays.asList("TopAAE"), null, null, null);

        // We must wrap the CM with its substream components into an AAE of itself. The CPE directly doesn't know how
        // to deal with the multiplier.
        //CpePipeline.runPipeline(reader, topAAE);
        CpeBuilder cpeBuilder = new CpeBuilder();
        cpeBuilder.setReader(reader);
        cpeBuilder.setAnalysisEngine(topTopAAE);

        cpeBuilder.setMaxProcessingUnitThreadCount(1);

        StatusCallbackListenerImpl status = new StatusCallbackListenerImpl();
        CollectionProcessingEngine engine = cpeBuilder.createCpe(status);

        engine.process();
        try {
            synchronized (status) {
                while (status.isProcessing) {
                    status.wait();
                }
            }
        } catch (InterruptedException e) {
            // Do nothing
        }

        if (status.exceptions.size() > 0) {
            throw new AnalysisEngineProcessException(status.exceptions.get(0));
        }


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
            Header header = JCasUtil.selectSingle(aJCas, Header.class);
            assertNotNull(header);
            FSArray otherIDs = header.getOtherIDs();
            assertNotNull(otherIDs, "Document with ID " + header.getId() + " does not have other ID annotations.");
            // Test for a single document that its PMC ID is set.
            // The pubmed ID is tested here but actually excluded in a newer version of the mapping file
            if (header.getDocId().equals("10097079")) {
                boolean pmcIdFound = false;
                for (int i = 0; i < otherIDs.size(); i++) {
                    OtherID otherID = (OtherID) otherIDs.get(i);
                    if (otherID.getSource().equals("pubmed"))
                        assertEquals(header.getDocId(), otherID.getId());
                    else if (otherID.getSource().equals("pmc")) {
                        assertEquals("PMC22336", otherID.getId());
                        pmcIdFound = true;
                    }
                    else
                        fail("Unexpected ArticleIdType: " + otherID.getSource());
                }
                assertTrue(pmcIdFound);
            }
        }
    }

    private static class StatusCallbackListenerImpl implements StatusCallbackListener {

        private final List<Throwable> exceptions = new ArrayList<>();

        private boolean isProcessing = true;

        public void entityProcessComplete(CAS arg0, EntityProcessStatus arg1) {
            if (arg1.isException()) {
                for (Throwable e : arg1.getExceptions()) {
                    exceptions.add(e);
                }
            }
        }

        public void aborted() {
            synchronized (this) {
                if (isProcessing) {
                    isProcessing = false;
                    notify();
                }
            }
        }

        public void batchProcessComplete() {
            // Do nothing
        }

        public void collectionProcessComplete() {
            synchronized (this) {
                if (isProcessing) {
                    isProcessing = false;
                    notify();
                }
            }
        }

        public void initializationComplete() {
            // Do nothing
        }

        public void paused() {
            // Do nothing
        }

        public void resumed() {
            // Do nothing
        }
    }
}

