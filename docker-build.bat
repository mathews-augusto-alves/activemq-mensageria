@echo off

REM Parar e remover o contêiner ativo
docker stop activemq-api
docker rm activemq-api

REM Remover a imagem antiga
docker rmi activemq-api

REM Limpar e empacotar o projeto Maven
mvn clean package && docker build -t activemq-api . && docker run --name activemq-api -p 8080:8080 -e "SPRING_PROFILES_ACTIVE=local" activemq-api

echo Deploy concluído!
pause
