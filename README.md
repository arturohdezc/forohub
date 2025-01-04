# Foro Hub

![Portada del Proyecto](https://drive.google.com/uc?id=1mva921QRVg45qMTZNiXB-XwyYugZGC_q)

![GitHub release (latest by date)](https://img.shields.io/github/v/release/usuario/forohub)
![GitHub release (last Commit)](https://img.shields.io/badge/last_commit-30%2F12%2F2024-brightgreen)
![GitHub issues](https://img.shields.io/github/issues/usuario/forohub)

## Índice

- [Descripción del Proyecto](#descripción-del-proyecto)
- [Requisitos Previos](#requisitos-previos)
- [Configuración del Proyecto](#configuración-del-proyecto)
- [Dependencias](#dependencias)
- [Funciones Implementadas](#funciones-implementadas)
- [Reglas de Negocio](#reglas-de-negocio)
- [Estado del Proyecto](#estado-del-proyecto)
- [Desarrolladores](#desarrolladores)
- [Conclusiones](#conclusiones)

## Descripción del Proyecto

**Foro Hub** es una API REST desarrollada en Java con Spring Boot que permite a los usuarios gestionar tópicos de discusión. El proyecto se centra en las operaciones básicas de CRUD (Crear, Leer, Actualizar, Eliminar) para tópicos, siguiendo las mejores prácticas del modelo REST.

### Funcionalidades Principales

- **CRUD de Tópicos:** Crear, mostrar, actualizar y eliminar tópicos.
- **Validaciones:** Implementación de reglas de negocio para asegurar la consistencia de los datos.
- **Persistencia:** Almacenamiento de información en una base de datos MySQL utilizando JPA.
- **Autenticación y Autorización:** Restricción de acceso mediante Spring Security.

## Requisitos Previos

- Java 17 o superior.
- Maven 4 o superior.
- MySQL 8 o superior.

## Configuración del Proyecto

1. Clonar el repositorio desde GitHub:

```bash
git clone https://github.com/arturohdezc/forohub.git
cd forohub
```

2. Configurar las variables de entorno:

```properties
spring.datasource.url=jdbc:mysql://localhost/forohub_api
spring.datasource.username=root
spring.datasource.password=12345678

jwt.secret=${JWT_SECRET:123456}
jwt.expiration=2
```

3. Instalar las dependencias utilizando Maven:

```bash
mvn clean install
```

4. Ejecutar la aplicación:

```bash
mvn spring-boot:run
```

## Dependencias

El proyecto utiliza las siguientes dependencias principales:

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.flywaydb</groupId>
        <artifactId>flyway-core</artifactId>
    </dependency>
    <dependency>
        <groupId>org.flywaydb</groupId>
        <artifactId>flyway-mysql</artifactId>
    </dependency>
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <dependency>
        <groupId>com.auth0</groupId>
        <artifactId>java-jwt</artifactId>
        <version>4.4.0</version>
    </dependency>
    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
        <version>2.7.0</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

## Funciones Implementadas

1. **Crear un Tópico:**
   - Endpoint: `POST /tópicos`
   - Requiere título, mensaje, autor y curso en el cuerpo de la solicitud.
   - Persistencia en base de datos con validaciones.

2. **Listar Tópicos:**
   - Endpoint: `GET /tópicos`
   - Devuelve todos los tópicos almacenados, incluyendo detalles como fecha de creación y estado.
   - Opcional: Paginación y orden por fecha de creación.

3. **Mostrar un Tópico Específico:**
   - Endpoint: `GET /tópicos/{id}`
   - Devuelve los detalles de un tópico por su ID.

4. **Actualizar un Tópico:**
   - Endpoint: `PUT /tópicos/{id}`
   - Permite modificar el título, mensaje, autor o curso de un tópico existente.

5. **Eliminar un Tópico:**
   - Endpoint: `DELETE /tópicos/{id}`
   - Elimina un tópico por su ID.

6. **Login del Usuario:**
   - Endpoint: `POST /auth/login`
     - Autenticación del usuario.
     - Devuelve un token Bearer para realizar solicitudes autenticadas.
    
**Demostación de las funciones**

  [![Foro Hub](https://img.freepik.com/vector-gratis/diseno-maqueta-plantilla-reproductor-video-negro_1017-36895.jpg)](https://drive.google.com/file/d/1YRnPsDw9IMCuEne8l0UCAgqeFhNJjXcn/view?usp=sharing)


## Reglas de Negocio

- Todos los campos son obligatorios.
- No se permiten tópicos duplicados (mismo título y mensaje).
- Validaciones de entrada implementadas con `@Valid`.

## Estado del Proyecto

El proyecto está en desarrollo con las siguientes funcionalidades integradas:

- Documentación con Swagger.
- Integración de pruebas unitarias y de integración.
- Mejora de la seguridad con OAuth2.

Las siguientes funcionalidades se agregaran en futuras actualizaciones:
- Implementación de rutas adicionales: `/usuario` y `/respuestas`.

### Nota
Se agregaron **getters**, **setters** y **constructores** manualmente en caso de que Lombok no sea identificado por su IDE.

## Desarrolladores

- Arturo Hernández

## Conclusiones

Foro Hub ofrece una solución robusta y escalable para gestionar tópicos de discusión. Con la integración de Spring Boot, MySQL y herramientas modernas, el proyecto sirve como base para desarrollos futuros con mayores capacidades y funcionalidades avanzadas.
