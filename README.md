# Sanos y Salvos — Microservicios Java Spring Boot

Plataforma de localización y recuperación de mascotas perdidas.  
Evaluación Parcial N°2 — Java: Diseño y Construcción de Soluciones Nativas en Nube

## Microservicios

| Servicio | Puerto | Descripción |
|---|---|---|
| ms-usuarios | 8081 | Gestión de usuarios registrados |
| ms-reportes | 8082 | Reportes de mascotas perdidas/encontradas |
| ms-matching | 8083 | Motor de coincidencias entre reportes |
| ms-geolocalizacion | 8084 | Zonas y coordenadas geográficas |
| ms-notificaciones | 8085 | Alertas y notificaciones a usuarios |

## Requisitos previos

- Java 17+
- Maven 3.8+
- PostgreSQL 14+

## Configuración de la base de datos

Crear las 5 bases de datos en PostgreSQL:

```sql
CREATE DATABASE db_usuarios;
CREATE DATABASE db_reportes;
CREATE DATABASE db_matching;
CREATE DATABASE db_geolocalizacion;
CREATE DATABASE db_notificaciones;
```

> Las credenciales por defecto son `usuario: postgres` / `contraseña: postgres`.  
> Si tienes credenciales distintas, edita el archivo `src/main/resources/application.properties` de cada microservicio.

## Clonar el repositorio

```bash
git clone https://github.com/TU_USUARIO/sanos-y-salvos-microservicios.git
cd sanos-y-salvos-microservicios
```

## Ejecutar un microservicio

```bash
# Entrar a la carpeta del microservicio
cd ms-usuarios

# Limpiar y compilar
mvn clean install

# Ejecutar
mvn spring-boot:run
```

Repetir el proceso para cada microservicio en terminales separadas.

## Generar el .jar ejecutable

```bash
mvn clean package
java -jar target/ms-usuarios-0.0.1-SNAPSHOT.jar
```

## Probar los endpoints con Postman

Importar la colección o hacer peticiones manualmente:

### ms-usuarios (puerto 8081)
- `GET    http://localhost:8081/api/usuarios`
- `GET    http://localhost:8081/api/usuarios/{id}`
- `POST   http://localhost:8081/api/usuarios`
- `PUT    http://localhost:8081/api/usuarios/{id}`
- `DELETE http://localhost:8081/api/usuarios/{id}`

### ms-reportes (puerto 8082)
- `GET    http://localhost:8082/api/mascotas`
- `POST   http://localhost:8082/api/mascotas`
- `GET    http://localhost:8082/api/reportes`
- `GET    http://localhost:8082/api/reportes/tipo/PERDIDO`
- `POST   http://localhost:8082/api/reportes`
- `PUT    http://localhost:8082/api/reportes/{id}`
- `DELETE http://localhost:8082/api/reportes/{id}`

### ms-matching (puerto 8083)
- `GET    http://localhost:8083/api/coincidencias`
- `GET    http://localhost:8083/api/coincidencias/altas`
- `POST   http://localhost:8083/api/coincidencias`
- `PUT    http://localhost:8083/api/coincidencias/{id}`
- `DELETE http://localhost:8083/api/coincidencias/{id}`

### ms-geolocalizacion (puerto 8084)
- `GET    http://localhost:8084/api/zonas`
- `POST   http://localhost:8084/api/zonas`
- `POST   http://localhost:8084/api/coordenadas/zona/{zonaId}`
- `GET    http://localhost:8084/api/coordenadas/zona/{zonaId}`

### ms-notificaciones (puerto 8085)
- `GET    http://localhost:8085/api/notificaciones`
- `POST   http://localhost:8085/api/notificaciones`
- `GET    http://localhost:8085/api/notificaciones/no-leidas`
- `PATCH  http://localhost:8085/api/notificaciones/{id}/leer`
- `DELETE http://localhost:8085/api/notificaciones/{id}`

## Estructura del proyecto

```
sanos-y-salvos-microservicios/
├── ms-usuarios/
│   └── src/main/java/cl/sanosysalvos/usuarios/
│       ├── controller/   ← REST endpoints
│       ├── service/      ← Lógica de negocio
│       ├── repository/   ← Acceso a datos (JPA)
│       └── model/        ← Entidades JPA
├── ms-reportes/
├── ms-matching/
├── ms-geolocalizacion/
└── ms-notificaciones/
```

## Tecnologías utilizadas

- Java 17
- Spring Boot 3.2.5
- Spring Data JPA / Hibernate
- PostgreSQL
- Maven

Proyecto finalizado y listo para revisión.
