package de.julielab.jcore.ae.biosem.bionlpst09;

import de.julielab.jcore.types.EventMention;
import de.julielab.jcore.types.Gene;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.junit.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestData {
    @Test
    public void testAE() throws Exception {
        JCas jCas = JCasFactory.createJCas("de.julielab.jcore.types.jcore-all-types");
        AnalysisEngine engine = AnalysisEngineFactory
                .createEngine("de.julielab.jcore.ae.biosem.desc.jcore-biosem-ae-bionlp-st09");
        jCas.setDocumentText("mTOR regulates AMPK");
        Gene g1 = new Gene(jCas, 0, 4);
        g1.addToIndexes();
        Gene g2 = new Gene(jCas, 15, 19);
        g2.addToIndexes();
        engine.process(jCas.getCas());
        Collection<EventMention> events = JCasUtil.select(jCas, EventMention.class);
        assertEquals(1, events.size());
        assertEquals("BioSemEventAnnotatorST09", events.iterator().next().getComponentId());
    }
}
