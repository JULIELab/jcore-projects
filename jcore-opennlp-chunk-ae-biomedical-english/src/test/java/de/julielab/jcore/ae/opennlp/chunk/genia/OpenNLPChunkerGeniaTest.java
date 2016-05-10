package de.julielab.jcore.ae.opennlp.chunk.genia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.junit.Test;

import de.julielab.jcore.types.Chunk;
import de.julielab.jcore.types.ChunkNP;
import de.julielab.jcore.types.ChunkPP;
import de.julielab.jcore.types.ChunkVP;
import de.julielab.jcore.types.GeniaPOSTag;
import de.julielab.jcore.types.Sentence;
import de.julielab.jcore.types.Token;

public class OpenNLPChunkerGeniaTest {
	@Test
	public void testPackage() throws Exception {
		JCas jCas = JCasFactory.createJCas("de.julielab.jcore.types.jcore-morpho-syntax-types");
		jCas.setDocumentText("The gene was regulated in the cell.");
		new Sentence(jCas, 0, 35).addToIndexes();
		createToken(jCas, 0, 3, "DT");
		createToken(jCas, 4, 8, "NN");
		createToken(jCas, 9, 12, "VBD");
		createToken(jCas, 13, 22, "VBN");
		createToken(jCas, 23, 25, "IN");
		createToken(jCas, 26, 29, "DT");
		createToken(jCas, 30, 34, "NN");
		createToken(jCas, 34, 35, ".");
		AnalysisEngine ae = AnalysisEngineFactory.createEngine("de.julielab.jcore.ae.opennlp.chunk.desc.jcore-opennlp-chunk-ae-biomedical-english");
		ae.process(jCas);
		FSIterator<Annotation> it = jCas.getAnnotationIndex(Chunk.type).iterator();
		assertTrue(it.hasNext());
		Chunk c = (Chunk) it.next();
		assertEquals("The gene", c.getCoveredText());
		assertEquals(ChunkNP.class, c.getClass());
		
		assertTrue(it.hasNext());
		c = (Chunk) it.next();
		assertEquals("was", c.getCoveredText());
		assertEquals(ChunkVP.class, c.getClass());

		assertTrue(it.hasNext());
		c = (Chunk) it.next();
		assertEquals("regulated", c.getCoveredText());
		assertEquals(ChunkVP.class, c.getClass());

		assertTrue(it.hasNext());
		c = (Chunk) it.next();
		assertEquals("in", c.getCoveredText());
		assertEquals(ChunkPP.class, c.getClass());
		

		assertTrue(it.hasNext());
		c = (Chunk) it.next();
		assertEquals("the cell.", c.getCoveredText());
		assertEquals(ChunkNP.class, c.getClass());
	}

	private Token createToken(JCas aJCas, int begin, int end, String pos) {
		Token t = new Token(aJCas, begin, end);
		FSArray postagarray = new FSArray(aJCas, 1);
		GeniaPOSTag posTag = new GeniaPOSTag(aJCas, begin, end);
		posTag.setValue(pos);
		postagarray.set(0, posTag);
		t.setPosTag(postagarray);
		posTag.addToIndexes();
		t.addToIndexes();
		return t;
	}
}
