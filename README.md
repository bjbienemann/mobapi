## MobAPI
Transportes urbanos API

### Requisitos
* [java 11+](https://adoptopenjdk.net/)
* [maven 3.6.3+](https://maven.apache.org/download.cgi)
* [docker](https://hub.docker.com/search?q=&type=edition&offering=community)

### Construir a API
```
mvn package -DskipTests
```
### Docker
criar/atualizar as imagens e rodar os containers
```
docker-compose up --build -d
```

### Atenção
* A integração das linas de ônibus e itinerários está sendo feita após a criação da estrutura do banco de dados, a API só estará disponível após carregar os dados.
* Caso não tenha o java ou o maven instalado é possível fazer um pull da imagem docker do docker hub.
```
docker pull bienemann/mobapi:latest
```