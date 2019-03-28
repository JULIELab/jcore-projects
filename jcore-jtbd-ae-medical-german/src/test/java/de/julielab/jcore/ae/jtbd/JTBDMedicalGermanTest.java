package de.julielab.jcore.ae.jtbd;

import de.julielab.jcore.types.Sentence;
import de.julielab.jcore.types.Token;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JTBDMedicalGermanTest {
	@Test
	public void testPackage() throws Exception {
		AnalysisEngine ae = AnalysisEngineFactory.createEngine("de.julielab.jcore.ae.jtbd.desc.jcore-jtbd-ae-medical-german");
		JCas jCas = ae.newJCas();
		jCas.setDocumentText("A very simple sentence.");
		new Sentence(jCas, 0, jCas.getDocumentText().length()).addToIndexes();
		ae.process(jCas);
		FSIterator<Annotation> it = jCas.getAnnotationIndex(Token.type).iterator();
		Token t;
		assertTrue(it.hasNext());
		t = (Token) it.next();
		assertEquals("A", t.getCoveredText());
		
		assertTrue(it.hasNext());
		t = (Token) it.next();
		assertEquals("very", t.getCoveredText());
		
		assertTrue(it.hasNext());
		t = (Token) it.next();
		assertEquals("simple", t.getCoveredText());
		
		assertTrue(it.hasNext());
		t = (Token) it.next();
		assertEquals("sentence", t.getCoveredText());
		
		assertTrue(it.hasNext());
		t = (Token) it.next();
		assertEquals(".", t.getCoveredText());
	}
}
