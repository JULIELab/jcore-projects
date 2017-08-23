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
These are the results of the 10-folds cross validation for JSBD:

All : 12446
Correct: 12421
fp (false positives) :21
fn (false negatives) :4
Recall :0.9996780684104628
Precision :0.9983121684616622
F-score :0.9989946515462259
ACCURACY : 0.9979913225132573
0: 0,999
1: 0,995
2: 0,997
3: 0,995
4: 0,997
5: 0,997
6: 0,999
7: 0,998
8: 1,000
9: 0,998
avg accuracy: 0,998
avg f-score: 0,999

Accuracy on cross validation: 0.9975798557915574

### Documentation
For further information please refer to the documentation, JSBD-x.pdf.

