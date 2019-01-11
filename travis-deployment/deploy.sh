#!/usr/bin/env bash
if [ "$TRAVIS_PULL_REQUEST" == 'false' ]; then
	echo "Executing deploy"
	if [ ! -f julie-xml-tools.jar ]; then
	    wget https://search.maven.org/remotecontent?filepath=de/julielab/julie-xml-tools/0.4.2/julie-xml-tools-0.4.2-xml-tools-assembly.jar --output-document julie-xml-tools.jar
	fi
	if [ ! -f julielab-maven-aether-utilities.jar ]; then
    	    wget https://oss.sonatype.org/content/repositories/releases/de/julielab/julielab-maven-aether-utilities/1.0.0/julielab-maven-aether-utilities-1.0.0-cli-assembly.jar --output-document julielab-maven-aether-utilities.jar
    fi

    modulestodeploy=


	for i in . `java -jar julie-xml-tools.jar pom.xml //module`; do
	    java -cp julielab-maven-aether-utilities.jar de.julielab.utilities.aether.apps.GetCoordinatesFromRawPom $i/pom.xml > coords.txt;
	    if [ ! "$?" -eq "0" ]; then
	        exit 1
	    fi
	    groupId=`grep 'GROUPID:' coords.txt | sed 's/^GROUPID: //'`
	    artifactId=`grep 'ARTIFACTID:' coords.txt | sed 's/^ARTIFACTID: //'`
	    version=`grep 'VERSION:' coords.txt | sed 's/^VERSION: //'`
	    packaging=`grep 'PACKAGING:' coords.txt | sed 's/^PACKAGING: //'`
	    artifactFile=$i/target/$artifactId-$version.$packaging
        if [[ ! $version =~ .*SNAPSHOT.* ]]; then
            echo "Checking if $groupId:$artifactId:$packaging:$version exists"
            csNotFound=`java -cp julielab-maven-aether-utilities.jar de.julielab.utilities.aether.apps.GetRemoteChecksums $groupId:$artifactId:$packaging:$version | grep '<checkums not found>'`
        fi
	    if [[ $version =~ .*SNAPSHOT.* ]] || [ "$csNotFound" == "<checkums not found>" ]; then
            echo "This is a SNAPSHOT or a release that has not yet been deployed. Deploying."
            modulestodeploy=$modulestodeploy,$i
	    fi
    done

    if [ ! -z "$modulestodeploy" ]; then
        echo "Deploying moduels $modulestodeploy"
        mvn deploy -T 1C -B -P sonatype-nexus-deployment --settings travis-deployment/mvnsettings.xml -DskipTests=true -pl $modulestodeploy
    else
        echo "Everything up to date, nothing to deploy"
    fi
else
	echo "Deploy not executed"
fi
