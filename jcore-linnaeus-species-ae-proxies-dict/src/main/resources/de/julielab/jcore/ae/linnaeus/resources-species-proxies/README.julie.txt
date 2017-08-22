All files in this directory have originally been loaded down from
https://sourceforge.net/projects/linnaeus/files/Entity_packs/, 'species-proxy-1.3.tgz'.
The properties.conf file has been changed to use the resources
internally, i.e. via classpath lookup rather than as files in the file system. This
allows us to package the resource files into the component JAR without further configuration
required.