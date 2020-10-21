## MobAPI
Avaliação técnica para desenvolvedor backend

## Requisitos para rodar (run) em docker
* [Instalar docker e docker-compose](https://hub.docker.com/search?q=&type=edition&offering=community)

* Fazer um pull para baixar a imagem docker do hub.
```
docker pull bienemann/mobapi:latest
```
* Rodar os containers
Obs: Para executar o compose sem fazer o build local é de fundamental importância o passo anterio para fazer o download da imagem construida.
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

## Requisitos para construir (build)
* [java 11+](https://adoptopenjdk.net/)
* [maven 3.6.3+](https://maven.apache.org/download.cgi)

#### Construir a API
```
mvn package -DskipTests
```
#### Criar/atualizar as imagens e rodar os containers
```
docker-compose up --build -d
```

#### Para remover os containers
```
docker-compose down
```
Add o "-v" caso tenha a necessidade de remover os volumes, removendo os volumes são eliminados os dados de bando e arquivo taxi.txt
```
docker-compose down -v
```

## Testes
Seguindo boas práticas os testes de integração não são exetudados em tempo de build, segue o comando para execução.
```
mvnw verify
```
## Doc API
A documentação pode ser acessada através da URN "/swagger-ui" precedida pela URL do servidor, segue o exemplo:
```
http://localhost:8080/swagger-ui
```
