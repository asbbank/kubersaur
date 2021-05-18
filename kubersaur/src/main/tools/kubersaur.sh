#!/bin/bash

if [ $1 == "build" ]; then

  timestamp=$(date + "%Y%m%d%H%M%S")
  echo $timestamp

  mvn clean install -DskipTests=true -Ddocker.image.tag=$timestamp -Pdeploy-docker

elif [ $1 == "prepare" ]; then

  cd code
  mvn -DskipTests cd.connect.pipeline:docker-manifest-collectosaur-plugin:1.2:mamasaur -f pom.xml
  cd ..
  java -jar ~/development/java/mustache/target/mustache-1.1-SNAPSHOT-jar-with-dependancies.jar prepare release --manifest ./code/target/connect-manifest.json

elif [ $1 == "create" ]; then
  java -jar ~/development/java/mustache/target/mustache-1.1-SNAPSHOT-jar-with-dependancies.jar $1 $2 $3 $4 $5 $6
fi