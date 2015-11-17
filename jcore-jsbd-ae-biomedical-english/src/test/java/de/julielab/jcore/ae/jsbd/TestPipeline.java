package de.julielab.jcore.ae.jsbd;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.util.XMLInputSource;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.julielab.jcore.types.Sentence;

public class TestPipeline {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestPipeline.class);
    private static final File ENTITY_ANNOTATOR_DESC = new File(
            "src/main/resources/de/julielab/jcore/ae/jsbd/desc/jcore-jsbd-biomedical-english.xml");

    private static final String testText = "Down-regulation of protein kinase C: a potential mechanism for 2-amino-3-methylimidazo[4,5-f]quinoline-mediated immunosuppression."
            + " Immunosuppressive mechanism of food-born mutagenic and carcinogenic heterocyclic amine, 2-amino-3-methylimidazo[4,5-f]quinoline (IQ), was studied in murine spleen cells."
            + " IQ suppressed the IL-2 secretion and its mRNA expression."
            + " Treatment of IQ also decreased PKC activity in both the membrane fraction and the cytosol fraction."
            + " Treatment of PMA resulted in the recovery of IQ-induced suppression of IL-2 production, but the addition of ionomycin (Io) had no effect."
            + " Subsequently, we examined the effect of IQ on the activation of NF-kappaB, AP-1, and NF-AT, which are key transcription factors for IL-2 transcription."
            + " The activation of NF-kappaB and AP-1 was markedly inhibited by IQ in PMA/PHA-stimulated cells."
            + " Meanwhile, NF-AT was not affected by IQ in PMA/Io-stimulated cells."
            + " These results suggest that the immunosuppression induced by IQ might be associated with the down-regulation of PKC and subsequent blockade of the activation of NF-kappaB and AP-1 in the IL-2 gene expression.";

    private XMLInputSource taggerXML = null;
    private ResourceSpecifier taggerSpec = null;
    private AnalysisEngine entityAnnotator = null;

    /**
     * test whether Annotator can be initialized properly from given descriptor
     */
    @Before
    public void testInitialize() {
        LOGGER.info("testInitialize()");

        try {
            taggerXML = new XMLInputSource(ENTITY_ANNOTATOR_DESC);
            taggerSpec = UIMAFramework.getXMLParser().parseResourceSpecifier(taggerXML);
            entityAnnotator = UIMAFramework.produceAnalysisEngine(taggerSpec);
        } catch (final Exception e) {
            LOGGER.error("testInitialize()", e);
        }

        if (entityAnnotator != null) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }

    }

    /**
     * test whether Annotator splits sentences correctly
     */
    @Test
    public void testSplitting() throws Exception {
        LOGGER.info("testSplitting()");
        JCas jCas = JCasFactory.createJCas("de.julielab.jcore.types.jcore-all-types");
        jCas.setDocumentText(testText);
        entityAnnotator.process(jCas);

        FSIterator<Annotation> sents = jCas.getAnnotationIndex(Sentence.type).iterator();
        int sCount = 0;
        while (sents.hasNext()) {
            Sentence sent = (Sentence) sents.next();
            sCount += 1;
        }
        assertTrue(sCount == 9);
    }
}
