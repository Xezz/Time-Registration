#!/bin/bash
rm ~/servers/apache-tomee-webprofile/webapps/t-regs.war
mvn clean install && cp trex-webapp/target/t-regs.war ~/servers/apache-tomee-webprofile/webapps/ && say Compilation succeeded and deployment finished!

