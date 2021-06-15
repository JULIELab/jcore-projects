package de.julielab.jcore.ae.linnaeus;

import de.julielab.jcore.types.Organism;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


// The test should be run when something is changed, but it takes too long to run on an automated basis.
@Disabled
public class DescriptorTest {
    @Test
    public void testDescriptor() throws Exception {
        AnalysisEngine engine = AnalysisEngineFactory
                .createEngine("de.julielab.jcore.ae.linnaeus.desc.jcore-linnaeus-species-ae-species-dict");
        assertNotNull(engine);

        final JCas jCas = JCasFactory.createJCas("de.julielab.jcore.types.jcore-semantics-biology-types");
        jCas.setDocumentText("Calendula (/kəˈlɛndjuːlə/),[1] is a genus of about 15–20 species[2] of annual and perennial herbaceous plants in the daisy family Asteraceae that are often known as marigolds.[3]:771 They are native to southwestern Asia, western Europe, Macaronesia, and the Mediterranean. Other plants are also known as marigolds, such as corn marigold, desert marigold, marsh marigold, and plants of the genus Tagetes. The genus name Calendula is a modern Latin diminutive of calendae, meaning \"little calendar\", \"little clock\" or possibly \"little weather-glass\".[4] The common name \"marigold\" [4] refers to the Virgin Mary. The most commonly cultivated and used member of the genus is the pot marigold (Calendula officinalis). Popular herbal and cosmetic products named 'calendula' invariably derive from C. officinalis.");

        engine.process(jCas);

        final Collection<Organism> organisms = JCasUtil.select(jCas, Organism.class);
        assertEquals(4, organisms.size());
    }
}
