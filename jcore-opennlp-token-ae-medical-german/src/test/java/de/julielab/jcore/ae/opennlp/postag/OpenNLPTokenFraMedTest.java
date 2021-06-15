package de.julielab.jcore.ae.opennlp.postag;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.jcas.JCas;
import org.junit.jupiter.api.Test;

public class OpenNLPTokenFraMedTest {
	@Test
	public void testPackage() throws Exception {
		JCas jCas = JCasFactory.createJCas("de.julielab.jcore.types.jcore-morpho-syntax-types");
		AnalysisEngine ae = AnalysisEngineFactory
				.createEngine("de.julielab.jcore.ae.opennlp.token.desc.jcore-opennlp-token-ae-medical-german");

	}
}
