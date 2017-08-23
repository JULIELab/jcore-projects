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
These are the results for the 10-folds-cross-validation for JTBD:

Results for 10-folds-crossvalidation for JTBD Biomed:

critical decisions: 22805
correct decisions: 21979
fp: 417
fn: 409
R: 0.9817312846167598
P: 0.9813806036792284
F: 0.9815559128260092
ACC = 0.9637798728349046

ACC in round 0: 0,952
ACC in round 1: 0,815
ACC in round 2: 0,960
ACC in round 3: 0,964
ACC in round 4: 0,957
ACC in round 5: 0,957
ACC in round 6: 0,960
ACC in round 7: 0,965
ACC in round 8: 0,948
ACC in round 9: 0,964
avg accuracy: 0,944
avg F-score: 0,971


### Documentation
For further information please refer to the documentation, JTBD-x.pdf.
