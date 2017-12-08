mvn install:install-file -DgroupId=com.oracle.jdbc -DartifactId=ojdbc7 -Dversion=12.1.0.2 -Dpackaging=jar -Dfile=ojdbc7.jar
mvn clean package -Ddb.ip=129.150.112.129 -Ddb.name=dev1.krisipnetwork.oraclecloud.internal -Ddb.user=WEBAPP2 -Ddb.pass=WEBAPP2
mvn flyway:migrate -Ddb.ip=129.150.112.129 -Ddb.name=dev1.krisipnetwork.oraclecloud.internal -Dflyway.user=WEBAPP2 -Dflyway.password=WEBAPP2
