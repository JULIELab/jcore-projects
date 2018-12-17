# JCoRe LINNAEUS Species Analysis Engine with Species Dictionary
A species mention recognition engine.

### Descriptor location

`de.julielab.jcore.ae.linnaeus.desc.jcore-linnaeus-species-ae-proxies-dict`

### Objective
JCoRe LINNAEUS Species AE is an UIMA Analysis Engine that wraps the [LINNAEUS](http://linnaeus.sourceforge.net/) species tagger as a UIMA component. It recognizes species mentions in document text and creates an `Organism` annotation for each mention. Each mention is also mapped to an [NCBI Taxonomy](https://www.ncbi.nlm.nih.gov/taxonomy) identifier stored as a `ResourceEntry` in `Organism#resourceEntryList`.
This JCoRe project useses the LINNAEUS base project together with the LINNAEUS species proxies dictionary. There exist three dictionary types for LINNAEUS:
| Dictionary Type | Description |
|-----------------|-------------|
| species         | Identifies concrete species name mentions in text.  |
| species proxies | Identifies concrete species name mentions as well as species clues like cell lines or expressions like 'patient' (would be mapped to human).  |
| genera proxies  | Small dictionary linking genus names ('Drosophila') to the most frequent member species in MEDLINE ('Drosophila' -> 'Drosophila Melanogaster').   |

### Requirements and Dependencies
The input and output of an AE is done via annotation objects. The classes corresponding to these objects are part of the [JCoRe Type System](https://github.com/JULIELab/jcore-base/tree/master/jcore-types).


### Using the AE - Descriptor Configuration
The descriptor given above. should be uses by name in an AAE descriptor, CPE or UIMA (fit) application. No further configuration is necessary.


**1. Parameters**

| Parameter Name | Parameter Type | Mandatory | Multivalued | Description |
|----------------|----------------|-----------|-------------|-------------|
| ConfigFile | String | yes | no | Path to or classpath location of the LINNAEUS configuration file. |

**2. Predefined Settings**

ConfigFile: `internal:/de/julielab/jcore/linnaeus/resources-species-proxies/properties.conf`


**3. Capabilities**

| Type | Input | Output |
|------|:-----:|:------:|
| de.julielab.jcore.types.Organism |  | `+` |
| de.julielab.jcore.types.ResourceEntry |  | `+` |


### Reference
* Gerner M., Nenadic, G. and Bergman, C. M. (2010) LINNAEUS: a species name identification system for biomedical literature. BMC Bioinformatics 11:85. 
* http://linnaeus.sourceforge.net/