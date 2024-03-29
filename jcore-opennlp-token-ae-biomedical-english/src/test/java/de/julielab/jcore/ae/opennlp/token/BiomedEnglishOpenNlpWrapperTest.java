package de.julielab.jcore.ae.opennlp.token;

import de.julielab.jcore.types.Sentence;
import de.julielab.jcore.types.Token;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BiomedEnglishOpenNlpWrapperTest {
    @Test
    public void testCreationAndProcessing() throws Exception {
        JCas jCas = JCasFactory.createJCas("de.julielab.jcore.types.jcore-morpho-syntax-types");
        AnalysisEngine ae = AnalysisEngineFactory.createEngineFromPath(
                "src/main/resources/de/julielab/jcore/ae/opennlp/token/desc/jcore-opennlp-token-ae-biomedical-english.xml");
        jCas.setDocumentText("Precise let-7 expression levels balance organ regeneration against tumor suppression.");
        Sentence s = new Sentence(jCas, 0, jCas.getDocumentText().length());
        s.addToIndexes();
        ae.process(jCas);
        Collection<Token> tokens = JCasUtil.select(jCas, Token.class);
        assertEquals(11, tokens.size());
    }
}
