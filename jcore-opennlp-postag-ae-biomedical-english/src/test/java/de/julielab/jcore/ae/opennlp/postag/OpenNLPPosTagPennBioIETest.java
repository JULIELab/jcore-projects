package de.julielab.jcore.ae.opennlp.postag;

import de.julielab.jcore.types.Sentence;
import de.julielab.jcore.types.Token;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OpenNLPPosTagPennBioIETest {
	@Test
	public void testPackage() throws Exception {
		JCas jCas = JCasFactory.createJCas("de.julielab.jcore.types.jcore-morpho-syntax-types");
		jCas.setDocumentText("The gene was regulated in the cell.");
		new Sentence(jCas, 0, 35).addToIndexes();
		new Token(jCas, 0, 3).addToIndexes();
		new Token(jCas, 4, 8).addToIndexes();
		new Token(jCas, 9, 12).addToIndexes();
		new Token(jCas, 13, 22).addToIndexes();
		new Token(jCas, 23, 25).addToIndexes();
		new Token(jCas, 26, 29).addToIndexes();
		new Token(jCas, 30, 34).addToIndexes();
		new Token(jCas, 34, 35).addToIndexes();
		AnalysisEngine ae = AnalysisEngineFactory.createEngine("de.julielab.jcore.ae.opennlp.postag.desc.jcore-opennlp-postag-ae-biomedical-english");
		ae.process(jCas);
		FSIterator<Annotation> it = jCas.getAnnotationIndex(Token.type).iterator();
		Token t = (Token) it.next();
		assertNotNull(t.getPosTag());
		assertEquals("DT", t.getPosTag(0).getValue());
		t = (Token) it.next();
		assertNotNull(t.getPosTag());
		assertEquals("NN", t.getPosTag(0).getValue());
		t = (Token) it.next();
		assertNotNull(t.getPosTag());
		assertEquals("VBD", t.getPosTag(0).getValue());
		t = (Token) it.next();
		assertNotNull(t.getPosTag());
		assertEquals("VBN", t.getPosTag(0).getValue());
		t = (Token) it.next();
		assertNotNull(t.getPosTag());
		assertEquals("IN", t.getPosTag(0).getValue());
		t = (Token) it.next();
		assertNotNull(t.getPosTag());
		assertEquals("DT", t.getPosTag(0).getValue());
		t = (Token) it.next();
		assertNotNull(t.getPosTag());
		assertEquals("NN", t.getPosTag(0).getValue());
		t = (Token) it.next();
		assertNotNull(t.getPosTag());
		assertEquals(".", t.getPosTag(0).getValue());

	}
}
