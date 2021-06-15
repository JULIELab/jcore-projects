package de.julielab.jcore.ae.banner;

import de.julielab.jcore.types.Gene;
import de.julielab.jcore.types.Sentence;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BannerAETest {
    @Test
    public void testDescriptor() throws Exception {
        // just tag a single sentence with a test model that actually used that sentence as training data.
        JCas jcas = JCasFactory.createJCas("de.julielab.jcore.types.jcore-morpho-syntax-types",
                "de.julielab.jcore.types.jcore-document-meta-types",
                "de.julielab.jcore.types.jcore-semantics-biology-types");
        // this is sentence P00055040A0000 from the test BC2GM train data in the jcore-banner-ae project
        jcas.setDocumentText(
                "Ten out-patients with pustulosis palmaris et plantaris were examined with direct immunofluorescence (IF) technique for deposition of fibrinogen, fibrin or its degradation products (FR-antigen) in affected and unaffected skin, together with heparin-precipitable fraction (HPF), cryoglobulin and total plasma fibrinogen in the blood.");
        new Sentence(jcas, 0, jcas.getDocumentText().length()).addToIndexes();

        AnalysisEngine bannerAe = null;
        bannerAe = AnalysisEngineFactory.createEngine("de.julielab.jcore.ae.banner.desc.jcore-banner-ae-biomedical-english");
        bannerAe.process(jcas);

        // expected result from the GENE.eval.small file:
        // P00055040A0000|116 125|fibrinogen
        // P00055040A0000|127 132|fibrin
        // P00055040A0000|158 167|FR-antigen
        // P00055040A0000|243 254|cryoglobulin
        // P00055040A0000|269 278|fibrinogen
        // However, we ignore the offsets because the eval offsets ignore white spaces
        List<Gene> geneList = new ArrayList<>(JCasUtil.select(jcas, Gene.class));
        assertEquals("fibrinogen", geneList.get(0).getCoveredText());
        assertEquals("fibrin", geneList.get(1).getCoveredText());
        assertEquals("FR-antigen", geneList.get(2).getCoveredText());
        assertEquals("cryoglobulin", geneList.get(3).getCoveredText());
        assertEquals("fibrinogen", geneList.get(4).getCoveredText());

    }

}
