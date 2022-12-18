#!/usr/bin/env bash
# This script is useful when the whole project should be deployed but some modules have already
# been deployed with the current version, e.g. due to bug fixes. Then, just deploying the base project
# with all its modules would fail due to the already deployed releases.
# If, however, all versions are newly deployed, just use a mvn deploy command on the project root,
# this is much quicker and also results in a single staging repository in Nexus instead of one project
# for each module.
# An alternative is to do the deployment of the root in any case - even with already deployed modules - and
# remove those modules manually in the Nexus UI from the staging repository. One can open the contents of
# a repository and browse it. With a right click on an item one can select to delete it.


echo "Executing deploy"
if [ ! -f julie-xml-tools.jar ]; then
    wget https://repo1.maven.org/maven2/de/julielab/julie-xml-tools/0.6.0/julie-xml-tools-0.6.0-xml-tools-assembly.jar --output-document julie-xml-tools.jar
else
    echo "julie-xml-tools.jar already exists and is not downloaded."
fi
if [ ! -f julielab-maven-aether-utilities.jar ]; then
  	    wget https://oss.sonatype.org/content/repositories/releases/de/julielab/julielab-maven-aether-utilities/1.1.4/julielab-maven-aether-utilities-1.1.4-cli-assembly.jar --output-document julielab-maven-aether-utilities.jar
  else
      echo "julielab-maven-aether-utilities.jar already exists and is not downloaded."
  fi

  modulestodeploy=


  echo "Collecting direct child modules of jcore-base and jcore-base itself for deployment"
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
      # SNAPSHOTS are deployed always anyway
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
      echo "Deploying the following projects: $modulestodeploy"
      if [[ "${modulestodeploy}" =~ ".," ]]; then
        echo "Deploying parent POM"
        mvn clean deploy -P sonatype-nexus-deployment -N
         modulestodeploy=$(printf '%s\n' "${modulestodeploy//.,/}")
         echo "Modules to deploy after removal of parent POM: $modulestodeploy"
      else
        echo "Parent POM was not included in the deployment list."
      fi
      mvn clean deploy -B -P sonatype-nexus-deployment -DskipTests=true -pl $modulestodeploy
  else
      echo "All modules up to date, skipping deployment."
  fi
