#!/bin/bash

echo "=== Iniciando Docker Swarm ==="
docker swarm init

echo ""
echo "=== Construyendo imagenes de los microservicios ==="
docker build -t sanosysalvos/ms-usuarios:latest ./ms-usuarios
docker build -t sanosysalvos/ms-reportes:latest ./ms-reportes
docker build -t sanosysalvos/ms-matching:latest ./ms-matching
docker build -t sanosysalvos/ms-geolocalizacion:latest ./ms-geolocalizacion
docker build -t sanosysalvos/ms-notificaciones:latest ./ms-notificaciones

echo ""
echo "=== Desplegando stack en Swarm ==="
docker stack deploy -c docker-compose.yml sanosysalvos

echo ""
echo "=== Servicios desplegados ==="
docker stack services sanosysalvos

echo ""
echo "=== Nodos del cluster ==="
docker node ls

echo ""
echo "Sistema Sanos y Salvos desplegado correctamente."
echo "Endpoints disponibles:"
echo "  ms-usuarios:       http://localhost:8081/api/usuarios"
echo "  ms-reportes:       http://localhost:8082/api/reportes"
echo "  ms-matching:       http://localhost:8083/api/coincidencias"
echo "  ms-geolocalizacion: http://localhost:8084/api/zonas"
echo "  ms-notificaciones: http://localhost:8085/api/notificaciones"
