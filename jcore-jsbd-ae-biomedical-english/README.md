JULIE Sentence Boundary Detector (JSBD)

Introduction
============
JSBD is a ML-based sentence splitter. It can be retrained on supported
training material and is thus neither language nor domain dependent.



Dependencies
============ 
JTBD is based on a slightly modified version of the machine learning toolkit MALLET (Version 2.0.x). The necessary libraries are included in the executable JAR (see below) and accessible via the JULIE Nexus artifact manager.


Usage
=====

To run JSBD just run the self-executing JAR "jsbd-&lt;version&gt;.jar". This will show the available modes.

To use the UIMA component with its delivered descriptor, you may specify `de.julielab.jcore.ae.jsbd.desc.jcore-jsbd-ae-biomedical-english.xml` for an import by name.

Documentation
==============
For further information please refer to the documentation, JSBD-x.pdf.

