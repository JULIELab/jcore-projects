package de.julielab.jcore.reader.xml;

import static org.junit.Assert.*;

import org.apache.uima.collection.CollectionReader;
import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.junit.Test;

import de.julielab.jules.types.pubmed.Header;

public class PubmedReaderTest {
	@Test
	public void testMedlineReader() throws Exception {
		 JCas jCas =
		 JCasFactory.createJCas("de.julielab.jcore.types.jcore-document-meta-pubmed-types",
		 "de.julielab.jcore.types.jcore-document-structure-types");
		CollectionReaderDescription medlineReaderDescription = CollectionReaderFactory
				.createReaderDescriptionFromPath("src/main/resources/de/julielab/jcore/reader/xml/desc/jcore-pubmed-reader.xml");
		CollectionReader medlineReader = CollectionReaderFactory.createReader(
				medlineReaderDescription, XMLReader.PARAM_INPUT_DIR,
				"src/test/resources/pubmedDocs");
		assertTrue(medlineReader.hasNext());
		medlineReader.getNext(jCas.getCas());
		
		assertEquals("7723770", JCasUtil.selectSingle(jCas, Header.class).getDocId());
		assertNotNull(jCas.getDocumentText());
		assertTrue(jCas.getDocumentText().length() > 0);
	}
}
