package de.julielab.jcore.ae.linnaeus;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.InvalidXMLException;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

// The test should be run when something is changed, but it takes too long to run on an automated basis.
@Ignore
public class DescriptorTest {
	@Test
	public void testDescriptor() throws InvalidXMLException, ResourceInitializationException, IOException {
		AnalysisEngine engine = AnalysisEngineFactory
				.createEngine("de.julielab.jcore.ae.linnaeus.desc.jcore-linnaeus-species-ae-species-dict");
		assertNotNull(engine);
	}
}
