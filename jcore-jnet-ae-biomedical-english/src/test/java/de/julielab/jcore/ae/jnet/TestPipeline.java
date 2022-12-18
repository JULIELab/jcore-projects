package de.julielab.jcore.ae.jnet;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.util.XMLInputSource;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestPipeline {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestPipeline.class);
    private static final File ENTITY_ANNOTATOR_DESC = new File(
            "src/main/resources/de/julielab/jcore/ae/jnet/desc/jcore-jnet-ae-biomedical-english.xml");

    /**
     * test whether Annotator can be initialized properly from given descriptor
     */
    @Test
    public void testInitialize() {
        LOGGER.debug("testInitialize()");
        AnalysisEngine entityAnnotator = null;

        XMLInputSource taggerXML;
        ResourceSpecifier taggerSpec;

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
}
