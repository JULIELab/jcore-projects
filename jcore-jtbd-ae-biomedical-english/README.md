# JULIE Lab Token Boundary Detector

**Descriptor Path**:
```
de.julielab.jcore.ae.jtbd.desc.jcore-jtbd-ae-biomedical-english
```

### Introduction
JTBD is a ML-based sentence splitter. It can be retrained on supported
training material and is thus neither language nor domain dependent.



### Dependencies
JTBD is based on a slightly modified version of the machine learning toolkit MALLET (Version 2.0.x). The
necessary libraries are included in the executable JAR (see below) and accessible via the JULIE Nexus artifact manager.


### Usage
To run JTBD just run the self-executing jar "jtbd-&lt;version&gt;.jar". This will show the available modes.

### Performance
We have evaluated JTBD on out tokenization corpus which we compiled for the biomedical domain. It consists of data from (manually annotated) material which we took from MEDLINE abstracts and a modified version of PennBioIE's underlying tokenization.
Currently, our tokenization corpus comprises about 36,000 sentences. An accuracy of 99,4% is reached on this data using 10-folds-cross-validation.<br> 
You will find the model trained on this data in the directory `resources`. 

### Documentation
For further information please refer to the documentation, JTBD-x.pdf.
