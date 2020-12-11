FROM openjdk:11-oracle

EXPOSE 9100

WORKDIR /app
COPY target/reference_architecture-*.jar /app/app.jar

CMD java -XX:MaxRAMPercentage=70 \
         -XX:OnOutOfMemoryError="kill -9 %p" \
         -jar /app/app.jar