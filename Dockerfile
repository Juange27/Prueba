FROM openjdk:17
COPY "./target/Parcial2-1.jar" "app.jar"
EXPOSE 8114
ENTRYPOINT [ "java", "-jar", "app.jar" ]


