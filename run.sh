#!/bin/bash

# baixar a imagem docker da api
docker bienemann/mobapi:latest

# rodar os containers de banco de api
docker-compose up -d