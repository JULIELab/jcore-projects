package de.julielab.jcore.ae.opennlp.postag;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.junit.Test;

import de.julielab.jcore.types.Sentence;
import de.julielab.jcore.types.Token;

public class OpenNLPPosTagPennBioIETest {
	@Test
	public void testPackage() throws Exception {
		JCas jCas = JCasFactory.createJCas("de.julielab.jcore.types.jcore-morpho-syntax-types");
		jCas.setDocumentText("The gene was regulated.");
		new Sentence(jCas, 0, 23).addToIndexes();
		new Token(jCas, 0, 3).addToIndexes();
		new Token(jCas, 4, 8).addToIndexes();
		new Token(jCas, 9, 12).addToIndexes();
		new Token(jCas, 13, 22).addToIndexes();
		new Token(jCas, 22, 23).addToIndexes();
		AnalysisEngine ae = AnalysisEngineFactory.createEngine("de.julielab.jcore.ae.opennlp.postag.desc.jcore-opennlp-postag-ae-pennbioie");
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
		assertEquals(".", t.getPosTag(0).getValue());

	}
}