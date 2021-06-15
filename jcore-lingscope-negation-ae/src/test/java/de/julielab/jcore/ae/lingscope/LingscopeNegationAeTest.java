package de.julielab.jcore.ae.lingscope;

import de.julielab.jcore.types.*;
import de.julielab.jcore.utility.JCoReTools;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
public class LingscopeNegationAeTest {
    @Test
    public void testWithoutLikelihoodDict() throws Exception {
        final JCas jCas = JCasFactory.createJCas("de.julielab.jcore.types.jcore-morpho-syntax-types", "de.julielab.jcore.types.jcore-semantics-mention-types");
        final AnalysisEngine engine = AnalysisEngineFactory.createEngine(LingscopePosAnnotator.class, LingscopePosAnnotator.PARAM_CUE_MODEL, "de/julielab/jcore/ae/lingscope/resources/negation_models/baseline_cue_all_both.model", LingscopePosAnnotator.PARAM_SCOPE_MODEL, "de/julielab/jcore/ae/lingscope/resources/negation_models/crf_scope_words_crf_all_both.model", LingscopePosAnnotator.PARAM_IS_NEGATION_ANNOTATOR, true);
        //final AnalysisEngine engine = AnalysisEngineFactory.createEngine("de.julielab.jcore.ae.lingscope.desc.jcore-lingscope-negation-ae");
        jCas.setDocumentText("The patient denied leg pain but complained about a headache.");
        new Sentence(jCas, 0, jCas.getDocumentText().length()).addToIndexes();
        addToken(jCas, 0, 3, "DT", "The");
        addToken(jCas, 4, 11, "NN", "patient");
        addToken(jCas, 12, 18, "VBD", "deny");
        addToken(jCas, 19, 22, "NN", "leg");
        addToken(jCas, 23, 27, "NN", "pain");
        addToken(jCas, 28, 31, "CC", "but");
        addToken(jCas, 32, 42, "VBD", "complain");
        addToken(jCas, 43, 48, "IN", "about");
        addToken(jCas, 49, 50, "DT", "a");
        addToken(jCas, 51, 59, "NN", "headache");
        addToken(jCas, 59, 60, ".", ".");

        engine.process(jCas);

        final LikelihoodIndicator indicator = JCasUtil.selectSingle(jCas, LikelihoodIndicator.class);
        assertThat(indicator.getBegin()).isEqualTo(12);
        assertThat(indicator.getEnd()).isEqualTo(18);
        assertThat(indicator.getLikelihood()).isEqualTo("negation");

        final Scope scope = JCasUtil.selectSingle(jCas, Scope.class);
        assertThat(scope.getCue() == indicator);
        assertThat(scope.getBegin()).isEqualTo(12);
        // That the scope is this long is actually an error of the CRF
        assertThat(scope.getEnd()).isEqualTo(59);

    }

    private void addToken(JCas jCas, int begin, int end, String posTag, String lemma) {
        final Token token = new Token(jCas, begin, end);
        final PennBioIEPOSTag p1 = new PennBioIEPOSTag(jCas, begin, end);
        p1.setValue(posTag);
        token.setPosTag(JCoReTools.addToFSArray(null, p1));
        final Lemma lemmaAnnotation = new Lemma(jCas, begin, end);
        lemmaAnnotation.setValue(lemma);
        token.setLemma(lemmaAnnotation);
        token.addToIndexes();
    }
}
