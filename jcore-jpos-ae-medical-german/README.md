# JPOS Framed  

**Descriptor Path**:
```
de.julielab.jcore.ae.jpos.desc.jcore-jpos-ae-medical-german
```

This model was trained on the confidential FRAMED corpus, a non-sharable collection of various German-language clinical
document types as described in [1].
The feature configuration for the training process as well as the model and the descriptor file can be found in the `src/main/resources` folder.
Performance in a 10-fold crossvalidation on the complete framed corpus with said feature configuration was
```
Overall performance: avg (standard deviation)
Accuracy: 0.976(0.002)
```

### Reference
**[1]** Johannes Hellrich, Franz Matthies, Erik Faessler and Udo Hahn: [Sharing Models and Tools for Processing German Clinical Text](http://ebooks.iospress.nl/volumearticle/39444). In: Ronald Cornet, Lăcrămioara Stoicu-Tivadar, Alexander Hörbst, Carlos Luis Parra Calderón, Stig Kjær Andersen, Mira Hercigonja-Szekeres (Eds.): *Digital Healthcare Empowering Europeans* [= Proceedings of MIE2015], 2015, pp. 734-738 (Studies in Health Technology and Informatics, 210).
