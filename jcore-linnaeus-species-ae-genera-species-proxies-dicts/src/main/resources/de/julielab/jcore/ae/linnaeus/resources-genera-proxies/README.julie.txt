All files in this directory have originally been loaded down from
http://linnaeus.sourceforge.net/ under 'Entity type dictionary packs', ‘genera-species-proxy-1.0.tar.gz’.
The properties.conf file has been changed to use the resources
internally, i.e. via classpath lookup rather than as files in the file system. This
allows us to package the resource files into the component JAR without further configuration
required.