package de.julielab.jcore.ae.mstparser;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.jcas.JCas;
import org.junit.Test;

public class TestModel {
    @Test
    public void testAE() throws Exception {
        JCas jCas = JCasFactory.createJCas("de.julielab.jcore.types.jcore-all-types");
        AnalysisEngine engine = AnalysisEngineFactory
                .createEngine("de.julielab.jcore.ae.mstparser.desc.jcore-mstparser-ae-biomedical-english");
    }
}
