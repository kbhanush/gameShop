# modified for IOUG Collaborate 18. Database prodDB on krisipnetwork 

mvn install:install-file -DgroupId=com.oracle.jdbc -DartifactId=ojdbc7 -Dversion=12.1.0.2 -Dpackaging=jar -Dfile=ojdbc7.jar
mvn clean package -Ddb.ip=129.150.89.253 -Ddb.name=krisdb.596600477.oraclecloud.internal -Ddb.user=WEBAPP2 -Ddb.pass=WEBAPP2
mvn flyway:migrate -Ddb.ip=129.150.89.253 -Ddb.name=krisdb.596600477.oraclecloud.internal -Dflyway.user=WEBAPP2 -Dflyway.password=WEBAPP2
