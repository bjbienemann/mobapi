## MobAPI
Avaliação técnica para desenvolvedor backend

### Requisitos para rodar (run) em docker
* [Instalar docker e docker-compose](https://hub.docker.com/search?q=&type=edition&offering=community)

* Fazer um pull para baixar a imagem docker do hub.
```
docker pull bienemann/mobapi:latest
```
* criar/atualizar as imagens e rodar os containers
```
docker-compose up
```
### Atenção
* A integração das linas de ônibus e itinerários está sendo feita após a criação da estrutura do banco de dados, a API só estará disponível ao fim do processo.

### Modelo de request
```
curl --location --request GET 'localhost:8080/onibus/linhas?nome=maio'
```
Segue as collections do Postman utilizadas
* [onibus_itinerarios.postman_collection.json](postman/onibus_itinerarios.postman_collection.json)
* [onibus_linhas.postman_collection.json](postman/onibus_linhas.postman_collection.json)
* [taxi.postman_collection.json](postman/taxi.postman_collection.json)

### Requisitos para construir (build)
* [java 11+](https://adoptopenjdk.net/)
* [maven 3.6.3+](https://maven.apache.org/download.cgi)

### Construir a API
```
mvn package -DskipTests
```
* criar/atualizar as imagens e rodar os containers
```
docker-compose up --build -d
```

### Teste de integração
Para fazer o teste de integração o objetivo(goal) verify 
```
mvnw verify
```
### Doc API
```
http://localhost:8080/swagger-ui
```
