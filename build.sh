mvn install:install-file -DgroupId=com.oracle.jdbc -DartifactId=ojdbc7 -Dversion=12.1.0.2 -Dpackaging=jar -Dfile=ojdbc7.jar
mvn install:install-file -DgroupId=javax.servlet -DartifactId=javax.servlet.servlet-api -Dversion=2.5 -Dpackaging=jar -Dfile=javax.servlet.javax.servlet-api.jar
mvn install:install-file -DgroupId=javax.servlet.jsp -DartifactId=javax.servlet.jsp-api -Dversion=2.1 -Dpackaging=jar -Dfile=javax.servlet.jsp.javax.servlet.jsp-api.jar
mvn clean install
#mvn flyway:migrate -Ddb.ip=129.157.186.185 -Ddb.name=PDB1.usoracle52544.oraclecloud.internal -Dflyway.user=system -Dflyway.password=Temp123#
mvn flyway:migrate -Ddb.ip=129.157.186.185 -Ddb.name=PDB1.usoracle52544.oraclecloud.internal -Dflyway.user=WEBAPP2 -Dflyway.password=WEBAPP2
