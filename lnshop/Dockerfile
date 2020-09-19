FROM adoptopenjdk/openjdk11-openj9:alpine-jre
ARG JAR_FILE=target/lnshop-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT [ "java", "-Xsoftmx48m", "-Xmx64m", "-Xss256k", "-XX:IdleTuningMinIdleWaitTime=60", "-Xtune:virtualized", "-XX:+CompactStrings", "-Xshareclasses:name=lnshop,cacheDir=/opt/app/jvm/openj9/shareclasses,verbose", "-Xscmx256m", "-jar", "app.jar", "--spring.profiles.active=prod" ]
EXPOSE 8080

# Prepackage jar first / Test fails on prepackaging due to missing environment variables
# ./mvnw clean package -Dmaven.test.skip=true
