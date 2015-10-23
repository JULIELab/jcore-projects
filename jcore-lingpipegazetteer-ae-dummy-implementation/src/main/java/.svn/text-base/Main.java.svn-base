 /**
  * 
  * Copyright (c) 2015, JULIE Lab.
  * All rights reserved. This program and the accompanying materials 
  * are made available under the terms of the GNU Affero General Public License (LGPL) v3.0
  */

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.util.XMLInputSource;


public class Main {

	public static void main(String[] args) {
		boolean failed = false;
		XMLInputSource taggerXML;
		try {
			taggerXML = new XMLInputSource(args[0]);

			ResourceSpecifier taggerSpec = UIMAFramework.getXMLParser()
					.parseResourceSpecifier(taggerXML);
			AnalysisEngine ae = UIMAFramework
					.produceAnalysisEngine(taggerSpec);
		} catch (Exception e) {
			failed = true;
			e.printStackTrace();
		}
		System.out.println(failed);
	}

}
