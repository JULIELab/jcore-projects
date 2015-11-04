package de.julielab.jcore.reader.xml;

import static org.junit.Assert.*;

import org.apache.uima.cas.FSIterator;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.junit.Test;

import de.julielab.jcore.types.AuthorInfo;
import de.julielab.jcore.types.Keyword;
import de.julielab.jcore.types.pubmed.Header;
import de.julielab.jcore.types.pubmed.ManualDescriptor;

public class MedlineReaderTest {
    @Test
    public void testMedlineReader() throws Exception {
        JCas jCas = JCasFactory.createJCas("de.julielab.jcore.types.jcore-document-meta-pubmed-types",
                "de.julielab.jcore.types.jcore-document-structure-types");
        CollectionReaderDescription medlineReaderDescription = CollectionReaderFactory.createReaderDescriptionFromPath(
                "src/main/resources/de/julielab/jcore/reader/xml/desc/jcore-medline-reader.xml");
        CollectionReader medlineReader = CollectionReaderFactory.createReader(medlineReaderDescription,
                XMLReader.PARAM_INPUT_FILE, "src/test/resources/medlineDocs/7723770.xml");
        assertTrue(medlineReader.hasNext());
        medlineReader.getNext(jCas.getCas());

        assertEquals("7723770", JCasUtil.selectSingle(jCas, Header.class).getDocId());
        assertNotNull(jCas.getDocumentText());
        assertTrue(jCas.getDocumentText().length() > 0);
    }
    
    @Test
	public void testKeywords() throws Exception {
		JCas jCas = JCasFactory
				.createJCas("de.julielab.jcore.types.jcore-document-meta-pubmed-types", "de.julielab.jcore.types.jcore-document-structure-types");
		CollectionReaderDescription medlineReaderDescription = CollectionReaderFactory
				.createReaderDescriptionFromPath("src/main/resources/de/julielab/jcore/reader/xml/desc/jcore-medline-reader.xml");
		CollectionReader medlineReader = CollectionReaderFactory.createReader(medlineReaderDescription, XMLReader.PARAM_INPUT_FILE,
				"src/test/resources/medlineDocs/26504747.xml");
		assertTrue(medlineReader.hasNext());
		medlineReader.getNext(jCas.getCas());

		assertEquals("26504747", JCasUtil.selectSingle(jCas, Header.class).getDocId());
		assertNotNull(jCas.getDocumentText());
		assertTrue(jCas.getDocumentText().length() > 0);

		FSIterator<Annotation> it = jCas.getAnnotationIndex(ManualDescriptor.type).iterator();
		assertTrue(it.hasNext());
		ManualDescriptor md = (ManualDescriptor) it.next();
		FSArray kwl = md.getKeywordList();
		Keyword kw = (Keyword) kwl.get(0);
		assertEquals("MTOR", kw.getName());
		kw = (Keyword) kwl.get(1);
		assertEquals("Oncogene", kw.getName());
		kw = (Keyword) kwl.get(2);
		assertEquals("PI3K/Akt signaling", kw.getName());
		kw = (Keyword) kwl.get(3);
		assertEquals("Thyroid", kw.getName());
		kw = (Keyword) kwl.get(4);
		assertEquals("mTOR Mutation", kw.getName());
	}
	
	@Test
	public void testAffiliations() throws Exception {
		JCas jCas = JCasFactory
				.createJCas("de.julielab.jcore.types.jcore-document-meta-pubmed-types", "de.julielab.jcore.types.jcore-document-structure-types");
		CollectionReaderDescription medlineReaderDescription = CollectionReaderFactory
				.createReaderDescriptionFromPath("src/main/resources/de/julielab/jcore/reader/xml/desc/jcore-medline-reader.xml");
		CollectionReader medlineReader = CollectionReaderFactory.createReader(medlineReaderDescription, XMLReader.PARAM_INPUT_FILE,
				"src/test/resources/medlineDocs/26504747.xml");
		assertTrue(medlineReader.hasNext());
		medlineReader.getNext(jCas.getCas());

		Header h = JCasUtil.selectSingle(jCas, Header.class);
		AuthorInfo ai = h.getAuthors(0);
		assertEquals("Molecular Endocrinology Section, Department of Molecular Oncology, King Faisal Specialist Hospital and Research Center, Riyadh, Kingdom of Saudi Arabia.", ai.getAffiliation());
		ai = h.getAuthors(1);
		assertEquals("Molecular Endocrinology Section, Department of Molecular Oncology, King Faisal Specialist Hospital and Research Center, Riyadh, Kingdom of Saudi Arabia ; Department of Zoology, King Saud University, Riyadh, Kingdom of Saudi Arabia.", ai.getAffiliation());
		ai = h.getAuthors(2);
		assertEquals("Molecular Endocrinology Section, Department of Molecular Oncology, King Faisal Specialist Hospital and Research Center, Riyadh, Kingdom of Saudi Arabia.", ai.getAffiliation());
		ai = h.getAuthors(3);
		assertEquals("Department of Pathology, King Faisal Specialist Hospital and Research Center, Riyadh, Kingdom of Saudi Arabia.", ai.getAffiliation());
		ai = h.getAuthors(4);
		assertEquals("Molecular Endocrinology Section, Department of Molecular Oncology, King Faisal Specialist Hospital and Research Center, Riyadh, Kingdom of Saudi Arabia.", ai.getAffiliation());
		ai = h.getAuthors(5);
		assertEquals("Department of Zoology, King Saud University, Riyadh, Kingdom of Saudi Arabia.", ai.getAffiliation());
		ai = h.getAuthors(6);
		assertEquals("Molecular Endocrinology Section, Department of Molecular Oncology, King Faisal Specialist Hospital and Research Center, Riyadh, Kingdom of Saudi Arabia.", ai.getAffiliation());
	}
}
