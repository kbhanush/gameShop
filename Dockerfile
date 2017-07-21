# The base image from which we are building
FROM java:8

EXPOSE 5000 

ADD /target/CIDemo.jar CIDemo.jar

ENTRYPOINT ["java", "-jar", "CIDemo.jar", "-httpPort=5000"]
