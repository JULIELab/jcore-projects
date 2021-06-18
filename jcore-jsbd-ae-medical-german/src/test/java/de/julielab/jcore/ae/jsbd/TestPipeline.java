package de.julielab.jcore.ae.jsbd;

import de.julielab.jcore.types.Sentence;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.util.XMLInputSource;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class TestPipeline {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestPipeline.class);
    private static final String ENTITY_ANNOTATOR_DESC = 
    		"de.julielab.jcore.ae.jsbd.desc.jcore-jsbd-ae-medical-german";

    private static final String testText =
    		"Frau XXX wurde am 10.12.85 aus der Gyn.-Abteilung nach Sectio (Eingriff problemlos, Kind gesund) bei V. a. zunehmende Aortendissektion bei Aneurysma der Aorta-desc.."
            + " Dieser Verdacht konnte im CT nicht bestätigt werden."
            + " Die Patientin wurde in der Abteilung Herz- und Gefäßchirurgie erfolgreich operiert."
            + " Intraoperativ kam es zu massiven Blutungen, weshalb die Patientin insgesamt 11 Erythrozyten, 11 FFP und 10 Thrombozytenkonzentrate erhielt."
            + " Postoperativ entwickelte die Patientin zweimal einen Pneumothorax, mit thorakalen Schmerzen und Dyspnoe einhergehend, welcher jeweils mittels Thorax-Drainage behandelt wurde."
            + " Zuletzt war die Patientin diesbezüglich beschwerdefrei."
            + " Wir übernahmen Frau XXX am 28.12.85 von unserer Intensivstation zur Fortführung der prophylaktischen Antibiotika-Therapie."
            + " Die Patientin wurde insgesamt 14 Tage mit Binotal und Certomycin behandelt."
            + " Die Klappenfunktion ist echokardiographisch gut, die LV-Funktion mit einer EF von ca. 30 % weiterhin eingeschränkt.";

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
//            taggerXML = new XMLInputSource(ENTITY_ANNOTATOR_DESC);
//            taggerSpec = UIMAFramework.getXMLParser().parseResourceSpecifier(taggerXML);
            entityAnnotator = AnalysisEngineFactory.createEngine(ENTITY_ANNOTATOR_DESC);
        } catch (final Exception e) {
        	System.out.println(e);
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
