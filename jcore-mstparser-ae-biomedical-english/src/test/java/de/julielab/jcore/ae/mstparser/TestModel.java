package de.julielab.jcore.ae.mstparser;

import de.julielab.jcore.types.DependencyRelation;
import de.julielab.jcore.types.PennBioIEPOSTag;
import de.julielab.jcore.types.Sentence;
import de.julielab.jcore.types.Token;
import de.julielab.jcore.utility.JCoReTools;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.junit.Test;

import javax.naming.NameNotFoundException;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestModel {
    @Test
    public void testAE() throws Exception {
        JCas jCas = JCasFactory.createJCas("de.julielab.jcore.types.jcore-all-types");
        AnalysisEngine engine = AnalysisEngineFactory
                .createEngine("de.julielab.jcore.ae.mstparser.desc.jcore-mstparser-ae-biomedical-english");
        jCas.setDocumentText("Triptonide acts as a novel antiprostate cancer agent.");
        new Sentence(jCas, 0, jCas.getDocumentText().length()).addToIndexes();
        addToken(jCas, 0, 10, "NNP");
        addToken(jCas, 11, 15, "VBZ");
        addToken(jCas, 16, 18, "IN");
        addToken(jCas, 19, 20, "DT");
        addToken(jCas, 21, 26, "JJ");
        addToken(jCas, 27, 39, "JJ");
        addToken(jCas, 40, 46, "NN");
        addToken(jCas, 47, 52, "NN");
        addToken(jCas, 52, 53, ".");
        engine.process(jCas);

        final Collection<DependencyRelation> depRels = JCasUtil.select(jCas, DependencyRelation.class);
        assertEquals(9, depRels.size());
        for (DependencyRelation depRel : depRels) {
            System.out.println(depRel.getCoveredText() + " " + depRel.getLabel());
        }
    }

    private void addToken(JCas jCas, int begin, int end, String pos) {
        final Token t1 = new Token(jCas, begin, end);
        final PennBioIEPOSTag posTag = new PennBioIEPOSTag(jCas, begin, end);
        posTag.setValue(pos);
        t1.setPosTag(JCoReTools.addToFSArray(null, posTag));
        t1.addToIndexes();
    }
}
