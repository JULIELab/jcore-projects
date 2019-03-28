package de.julielab.jcore.ae.biosem.bionlpst11;

import de.julielab.jcore.types.EventMention;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.impl.XmiCasDeserializer;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.junit.Test;

import java.io.FileInputStream;

import static org.junit.Assert.assertTrue;

/**
 * With 'difficult document' we mean a document that used to make problems with BioSem
 */
public class TestDifficultDocument {
	@Test
	public void testDifficultDoc1() throws Exception {
		JCas jCas = JCasFactory
				.createJCas("de.julielab.jcore.types.jcore-all-types");
		XmiCasDeserializer.deserialize(new FileInputStream("src/test/resources/11250876.xmi"), jCas.getCas());
		AnalysisEngine engine = AnalysisEngineFactory
				.createEngine("de.julielab.jcore.ae.biosem.desc.jcore-biosem-ae-bionlp-st11");
		engine.process(jCas);
		FSIterator<Annotation> it = jCas.getAnnotationIndex(EventMention.type).iterator();
		assertTrue(it.hasNext());
	}
	
	@Test
	public void testDifficultDoc2() throws Exception {
		JCas jCas = JCasFactory
				.createJCas("de.julielab.jcore.types.jcore-all-types");
		XmiCasDeserializer.deserialize(new FileInputStream("src/test/resources/10806352.xmi"), jCas.getCas());
		AnalysisEngine engine = AnalysisEngineFactory
				.createEngine("de.julielab.jcore.ae.biosem.desc.jcore-biosem-ae-bionlp-st11");
		engine.process(jCas);
		FSIterator<Annotation> it = jCas.getAnnotationIndex(EventMention.type).iterator();
		assertTrue(it.hasNext());
	}
	
	@Test
	public void testDifficultDoc3() throws Exception {
		JCas jCas = JCasFactory
				.createJCas("de.julielab.jcore.types.jcore-all-types");
		XmiCasDeserializer.deserialize(new FileInputStream("src/test/resources/20935495.xmi"), jCas.getCas());
		AnalysisEngine engine = AnalysisEngineFactory
				.createEngine("de.julielab.jcore.ae.biosem.desc.jcore-biosem-ae-bionlp-st11");
		engine.process(jCas);
		FSIterator<Annotation> it = jCas.getAnnotationIndex(EventMention.type).iterator();
		assertTrue(it.hasNext());
	}
	
	@Test
	public void testDifficultDoc4() throws Exception {
		JCas jCas = JCasFactory
				.createJCas("de.julielab.jcore.types.jcore-all-types");
		XmiCasDeserializer.deserialize(new FileInputStream("src/test/resources/21681342.xmi"), jCas.getCas());
		AnalysisEngine engine = AnalysisEngineFactory
				.createEngine("de.julielab.jcore.ae.biosem.desc.jcore-biosem-ae-bionlp-st11");
		engine.process(jCas);
		FSIterator<Annotation> it = jCas.getAnnotationIndex(EventMention.type).iterator();
		assertTrue(it.hasNext());
	}
	
	@Test
	public void testDifficultDoc5() throws Exception {
		JCas jCas = JCasFactory
				.createJCas("de.julielab.jcore.types.jcore-all-types");
		XmiCasDeserializer.deserialize(new FileInputStream("src/test/resources/22675210.xmi"), jCas.getCas());
		AnalysisEngine engine = AnalysisEngineFactory
				.createEngine("de.julielab.jcore.ae.biosem.desc.jcore-biosem-ae-bionlp-st11");
		engine.process(jCas);
		FSIterator<Annotation> it = jCas.getAnnotationIndex(EventMention.type).iterator();
		assertTrue(it.hasNext());
	}
	
	@Test
	public void testDifficultDoc6() throws Exception {
		JCas jCas = JCasFactory
				.createJCas("de.julielab.jcore.types.jcore-all-types");
		XmiCasDeserializer.deserialize(new FileInputStream("src/test/resources/23380144.xmi"), jCas.getCas());
		AnalysisEngine engine = AnalysisEngineFactory
				.createEngine("de.julielab.jcore.ae.biosem.desc.jcore-biosem-ae-bionlp-st11");
		engine.process(jCas);
		FSIterator<Annotation> it = jCas.getAnnotationIndex(EventMention.type).iterator();
		assertTrue(it.hasNext());
	}
	
	@Test
	public void testDifficultDoc7() throws Exception {
		JCas jCas = JCasFactory
				.createJCas("de.julielab.jcore.types.jcore-all-types");
		XmiCasDeserializer.deserialize(new FileInputStream("src/test/resources/2806624.xmi"), jCas.getCas());
		AnalysisEngine engine = AnalysisEngineFactory
				.createEngine("de.julielab.jcore.ae.biosem.desc.jcore-biosem-ae-bionlp-st11");
		engine.process(jCas);
		FSIterator<Annotation> it = jCas.getAnnotationIndex(EventMention.type).iterator();
		assertTrue(it.hasNext());
	}
	
}
