# JCoRe Projects

These are pre-built projects for JCoRe Base modules that feature models trained on corpora from the biomedical and medical domain as described in (please cite this paper if you publish results using JCoRe):

```Udo Hahn, Franz Matthies, Erik Faessler and Johannes Hellrich: UIMA-Based JCoRe 2.0 Goes GitHub and Maven Central ― State-of-the-Art Software Resource Engineering and Distribution of NLP Pipelines. In: Nicoletta Calzolari (Conference Chair), Khalid Choukri, Thierry Declerck, Marko Grobelnik, Bente Maegaard, Joseph Mariani, Asuncion Moreno, Jan Odijk, Stelios Piperidis (Eds.): Proceedings of the Tenth International Conference on Language Resources and Evaluation (LREC 2016), 2016. Portorož, Slovenia.``` [[Full Text](http://www.lrec-conf.org/proceedings/lrec2016/pdf/774_Paper.pdf)]

### Maven Artifacts

If not stated otherwise, all the components found in this project are in their release version also available as Maven artifacts:
```
<dependency>
    <groupId>de.julielab</groupId>
    <artifactId>#COMPONENT-NAME</artifactId>
    <version>${jcore-version}</version>
</dependency>
```
Where `#COMPONENT-NAME` is exactly the same as the name on GitHub. For instance, to get a version of the BioSEM Annotator that was trained on the BioNLP SharedTask'11 data, include this in your Maven dependencies:
```
<dependency>
    <groupId>de.julielab</groupId>
    <artifactId>jcore-biosem-ae-bionlp-st11</artifactId>
    <version>${jcore-version}</version>
</dependency>
```
All other dependencies (e.g. the BioSEM Annotator itself) don't need to be declared as they are handled by the project.

The version variable `${jcore-version}` is defined in the jcore-parent pom and should not be edited manually, as it ensures compatibility.
