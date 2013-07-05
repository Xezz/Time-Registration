#!/bin/bash
rm -rf ~/servers/apache-tomee-webprofile/webapps/t-regs.war
mvn clean install && cp trex-webapp/target/t-regs.war ~/servers/apache-tomee-webprofile/webapps/
say Can not hear you through the SOUND of how AWESOME I R

