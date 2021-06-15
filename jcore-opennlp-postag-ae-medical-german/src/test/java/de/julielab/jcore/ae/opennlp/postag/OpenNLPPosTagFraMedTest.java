package de.julielab.jcore.ae.opennlp.postag;

import de.julielab.jcore.types.Sentence;
import de.julielab.jcore.types.Token;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OpenNLPPosTagFraMedTest {
	@Test
	public void testPackage() throws Exception {
		JCas jCas = JCasFactory.createJCas("de.julielab.jcore.types.jcore-morpho-syntax-types");
		jCas.setDocumentText("Kleinere hilusnahe Fistelungen werden mit Tacho-Comb-Vlies abgedeckt.");
		new Sentence(jCas, 0, 69).addToIndexes();
		
		new Token(jCas, 0, 8).addToIndexes();
		new Token(jCas, 9, 18).addToIndexes();
		new Token(jCas, 19, 30).addToIndexes();
		new Token(jCas, 31, 37).addToIndexes();
		new Token(jCas, 38, 41).addToIndexes();
		new Token(jCas, 41, 57).addToIndexes();
		new Token(jCas, 58, 67).addToIndexes();
		new Token(jCas, 68, 69).addToIndexes();
		AnalysisEngine ae = AnalysisEngineFactory.createEngine("de.julielab.jcore.ae.opennlp.postag.desc.jcore-opennlp-postag-ae-medical-german");
		ae.process(jCas);
		FSIterator<Annotation> it = jCas.getAnnotationIndex(Token.type).iterator();
		Token t = (Token) it.next();
		assertNotNull(t.getPosTag());
		assertEquals("ADJA", t.getPosTag(0).getValue());
		t = (Token) it.next();
		assertNotNull(t.getPosTag());
		assertEquals("ADJD", t.getPosTag(0).getValue());
		t = (Token) it.next();
		assertNotNull(t.getPosTag());
		assertEquals("NN", t.getPosTag(0).getValue());
		t = (Token) it.next();
		assertNotNull(t.getPosTag());
		assertEquals("VAFIN", t.getPosTag(0).getValue());
		t = (Token) it.next();
		assertNotNull(t.getPosTag());
		assertEquals("APPR", t.getPosTag(0).getValue());
		t = (Token) it.next();
		assertNotNull(t.getPosTag());
		assertEquals("NN", t.getPosTag(0).getValue());
		t = (Token) it.next();
		assertNotNull(t.getPosTag());
		assertEquals("PTKVZ", t.getPosTag(0).getValue()); // actually it is "VVPP" in FraMed corpus
		t = (Token) it.next();
		assertNotNull(t.getPosTag());
		assertEquals("$.", t.getPosTag(0).getValue());

	}
}
