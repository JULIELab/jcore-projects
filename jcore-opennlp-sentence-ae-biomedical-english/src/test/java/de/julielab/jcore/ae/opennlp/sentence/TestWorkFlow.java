package de.julielab.jcore.ae.opennlp.sentence;

import java.io.IOException;

import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.jcas.JCas;
import org.junit.Test;

public class TestWorkFlow {
    @Test
    public void testInitialization() throws UIMAException, IOException {
        JCas jCas = JCasFactory.createJCas("de.julielab.jcore.types.jcore-morpho-syntax-types");
        AnalysisEngine ae = AnalysisEngineFactory
                .createEngine("de.julielab.jcore.ae.opennlp.sentence.desc.jcore-opennlp-sentence-biomedical-english");
    }
}
