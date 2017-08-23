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

critical decisions: 22805<br>
correct decisions: 21979<br>
fp: 417<br>
fn: 409<br>
R: 0.9817312846167598<br>
P: 0.9813806036792284<br>
F: 0.9815559128260092<br>
ACC = 0.9637798728349046<br>

ACC in round 0: 0,952<br>
ACC in round 1: 0,815<br>
ACC in round 2: 0,960<br>
ACC in round 3: 0,964<br>
ACC in round 4: 0,957<br>
ACC in round 5: 0,957<br>
ACC in round 6: 0,960<br>
ACC in round 7: 0,965<br>
ACC in round 8: 0,948<br>
ACC in round 9: 0,964<br>
avg accuracy: 0,944<br>
avg F-score: 0,971


### Documentation
For further information please refer to the documentation, JTBD-x.pdf.
