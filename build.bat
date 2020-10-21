@echo off

:: construir a api 
mvn clean package -DskipTests

:: parar os containers e remover os volumes
docker-compose down -v

:: criar/atualizar as imagens e rodar os containers
docker-compose up --build -d