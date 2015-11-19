# JCoRe Projects

These are pre-built projects for JCoRe Base modules that feature models trained on corpora from the biomedical domain.

### Maven Artifacts

If not stated otherwise, all the components found in this project are in their release version also available as Maven artifacts:
```
<dependency>
    <groupId>de.julielab</groupId>
    <artifactId>#COMPONENT-NAME</artifactId>
    <version>${jcore-version}</version>
</dependency>
```
For instance, to get a version of the BioSEM Annotator that was trained on the BioNLP SharedTask'11 data, include this in your Maven dependencies:
```
<dependency>
    <groupId>de.julielab</groupId>
    <artifactId>jcore-biosem-ae-bionlp-st11</artifactId>
    <version>${jcore-version}</version>
</dependency>
```
All other dependencies (e.g. the BioSEM Annotator itself) don't need to be declared as they are handled by the project.

The version variable `${jcore-version}` is defined in the jcore-parent pom and should not be edited manually, as it ensures compatibility.
