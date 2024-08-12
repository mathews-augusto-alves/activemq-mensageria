# Usar a imagem base do OpenJDK 17
FROM openjdk:17-jdk-alpine

# Criar um diretório temporário dentro do container para armazenar arquivos temporários
VOLUME /tmp

# Copiar o arquivo JAR gerado no build do Maven para o diretório de trabalho no container
COPY target/activemq-0.0.1-SNAPSHOT.jar app.jar

# Definir o ponto de entrada para o container, especificando o comando para iniciar a aplicação Java
ENTRYPOINT ["java","-jar","/app.jar","--spring.profiles.active=local"]

# Expor a porta 8084 que a aplicação vai usar
EXPOSE 8084
