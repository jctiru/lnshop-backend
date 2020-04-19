FROM adoptopenjdk/openjdk11-openj9:alpine-jre
ARG JAR_FILE=target/lnshop-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT [ "java", "-XX:MaxRAM=128m", "-Xss512k", "-Xtune:virtualized", "-XX:+CompactStrings", "-Xshareclasses:cacheDir=/opt/app/jvm/openj9/shareclasses,verbose", "-Xscmx128m", "-jar", "app.jar", "--spring.profiles.active=prod" ]
EXPOSE 8080

# Prepackage jar first / Test fails on prepackaging due to missing environment variables
# ./mvnw clean package -Dmaven.test.skip=true
