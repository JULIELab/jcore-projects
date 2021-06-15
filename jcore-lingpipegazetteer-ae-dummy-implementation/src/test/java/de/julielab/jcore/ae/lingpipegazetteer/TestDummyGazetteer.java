package de.julielab.jcore.ae.lingpipegazetteer;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.jcas.JCas;
import org.junit.jupiter.api.Test;

public class TestDummyGazetteer {
    @Test
    public void testAE() throws Exception {
        JCas jCas = JCasFactory.createJCas("de.julielab.jcore.types.jcore-all-types");
        AnalysisEngine engine = AnalysisEngineFactory
                .createEngine("de.julielab.jcore.ae.lingpipegazetteer.dummy.desc.ApproxGazetteerAnnotatorTest");
    }
}
