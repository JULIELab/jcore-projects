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

All: 12446<br>
Correct: 12421<br>
fp (false positives): 21<br>
fn (false negatives): 4<br>
Recall: 0.9996780684104628<br>
Precision: 0.9983121684616622<br>
F-score: 0.9989946515462259<br>
ACCURACY: 0.9979913225132573<br>
0: 0,999<br>
1: 0,995<br>
2: 0,997<br>
3: 0,995<br>
4: 0,997<br>
5: 0,997<br>
6: 0,999<br>
7: 0,998<br>
8: 1,000<br>
9: 0,998<br>
avg accuracy: 0,998<br>
avg f-score: 0,999<br>

Accuracy on cross validation: 0.9975798557915574

### Documentation
For further information please refer to the documentation, JSBD-x.pdf.

