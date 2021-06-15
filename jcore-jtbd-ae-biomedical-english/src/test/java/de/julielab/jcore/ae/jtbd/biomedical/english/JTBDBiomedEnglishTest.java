package de.julielab.jcore.ae.jtbd.biomedical.english;

import de.julielab.jcore.types.Sentence;
import de.julielab.jcore.types.Token;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JTBDBiomedEnglishTest {
	@Test
	public void testPackage() throws Exception {
		AnalysisEngine ae = AnalysisEngineFactory.createEngine("de.julielab.jcore.ae.jtbd.desc.jcore-jtbd-ae-biomedical-english");
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
