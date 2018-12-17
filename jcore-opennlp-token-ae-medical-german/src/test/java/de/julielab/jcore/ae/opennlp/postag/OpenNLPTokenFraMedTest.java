package de.julielab.jcore.ae.opennlp.postag;

import static org.junit.Assert.*;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.junit.Test;

import de.julielab.jcore.types.Sentence;
import de.julielab.jcore.types.Token;

public class OpenNLPTokenFraMedTest {
	@Test
	public void testPackage() throws Exception {
		JCas jCas = JCasFactory.createJCas("de.julielab.jcore.types.jcore-morpho-syntax-types");
		AnalysisEngine ae = AnalysisEngineFactory
				.createEngine("de.julielab.jcore.ae.opennlp.token.desc.jcore-opennlp-token-ae-medical-german");

	}
}
