 #!/bin/sh

mkdir -p $CATALINA_HOME/webapps/test

cp -r src/main/webapp/* $CATALINA_HOME/webapps/test

cp -r target/classes $CATALINA_HOME/webapps/test/WEB-INF

cp -r lib $CATALINA_HOME/webapps/test/WEB-INF
