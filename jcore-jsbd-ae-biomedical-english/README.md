# JULIE Sentence Boundary Detector (JSBD)  

**Descriptor Path**:
```
de.julielab.jcore.ae.jsbd.desc.jcore-jsbd-ae-biomedical-english
```

### Introduction
JSBD is a ML-based sentence splitter. It can be retrained on supported
training material and is thus neither language nor domain dependent.



### Dependencies
JSBD is based on a slightly modified version of the machine learning toolkit MALLET (Version 2.0.x). The necessary libraries are included in the executable JAR (see below) and accessible via the JULIE Nexus artifact manager.


### Usage
To run JSBD just run the self-executing JAR "jsbd-&lt;version&gt;.jar". This will show the available modes.

To use the UIMA component with its delivered descriptor, you may specify `de.julielab.jcore.ae.jsbd.desc.jcore-jsbd-ae-biomedical-english` for an import by name.

### Performance
JSBD was developed and optimized for the biomedical domain. However, when training it on respective corpora, it may also be used for other domains. We have evaluated JSBD on our data which we compiled for the bio-medical domain. It
consists of data from both the GENIA [?] and the PennBioIE 7 corpus and additional
material which we took from MedLine abstracts.<br>
Currently, it comprises about 62000 sentences. An accuracy of 99.7% is yielded on this
data using 10-fold cross-validation and the f-score averages 99.9%.<br>
You will find the model trained on this data in the directory `resources`

### Documentation
For further information please refer to the documentation, JSBD-x.pdf.

