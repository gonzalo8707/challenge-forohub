# 🗣️ ForoHub - Challenge Backend ONE

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.3-brightgreen?style=for-the-badge&logo=springboot)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=for-the-badge&logo=mysql)
![JWT](https://img.shields.io/badge/JWT-Auth-black?style=for-the-badge&logo=jsonwebtokens)
![Status](https://img.shields.io/badge/Status-Completado-success?style=for-the-badge)

## 📋 Descripción

ForoHub es una API REST desarrollada como Challenge del programa Oracle ONE + Alura Latam. Replica el funcionamiento de un foro, donde los usuarios pueden crear, consultar, actualizar y eliminar tópicos de discusión, con autenticación segura mediante JWT.

## ✨ Funcionalidades

- 🔐 **Autenticación** con JWT (JSON Web Token)
- 📝 **Registrar** un nuevo tópico
- 📋 **Listar** todos los tópicos con paginación
- 🔍 **Detallar** un tópico específico por ID
- ✏️ **Actualizar** un tópico existente
- 🗑️ **Eliminar** un tópico

## 🛠️ Tecnologías utilizadas

| Tecnología | Versión |
|---|---|
| Java | 17 |
| Spring Boot | 4.0.3 |
| Spring Security | 7.0.3 |
| Spring Data JPA | 4.0.3 |
| Flyway Migration | - |
| MySQL | 8.0 |
| Auth0 JWT | 4.4.0 |
| Lombok | - |
| Maven | 4.x |

## 🗄️ Estructura de la Base de Datos

```
usuarios
├── id (PK)
├── nombre
├── correo_electronico (UNIQUE)
└── contrasena (BCrypt)

topicos
├── id (PK)
├── titulo
├── mensaje
├── fecha_creacion
├── status (ACTIVO/CERRADO/SOLUCIONADO)
├── autor
├── curso
├── autor_id (FK → usuarios)
└── curso_id (FK → cursos)

cursos
├── id (PK)
├── nombre
└── categoria

respuestas
├── id (PK)
├── mensaje
├── topico_id (FK → topicos)
├── fecha_creacion
├── autor_id (FK → usuarios)
└── solucion (BOOLEAN)
```

## 📁 Estructura del Proyecto

```
src/main/java/com/Gonzalo/forohub/
├── Controller/
│   ├── AutenticacionController.java
│   └── TopicoController.java
├── domain/
│   ├── topico/
│   │   ├── Topico.java
│   │   ├── TopicoRepository.java
│   │   ├── DatosRegistroTopico.java
│   │   ├── DatosActualizarTopico.java
│   │   └── StatusTopico.java
│   └── usuario/
│       ├── Usuario.java
│       ├── UsuarioRepository.java
│       └── DatosAutenticacionUsuario.java
├── infra/
│   └── security/
│       ├── SecurityConfigurations.java
│       ├── SecurityFilter.java
│       ├── TokenService.java
│       └── AutenticacionService.java
└── ForohubApplication.java

src/main/resources/
├── db/migration/
│   ├── V1__create-table-usuarios.sql
│   ├── V2__create-table-cursos.sql
│   ├── V3__create-table-topicos.sql
│   ├── V4__create-table-respuestas.sql
│   ├── V5__add-columns-autor-curso.sql
│   └── V6__alter-autor-curso-nullable.sql
└── application.properties
```

## 🚀 Cómo ejecutar el proyecto

### Pre-requisitos

- Java 17+
- Maven 4+
- MySQL 8+
- IntelliJ IDEA (recomendado)

### Configuración

1. **Clona el repositorio**
```bash
git clone https://github.com/Gonzalo8707/Challenge-ForoHub.git
cd Challenge-ForoHub
```

2. **Crea la base de datos en MySQL**
```sql
CREATE DATABASE forohub;
```

3. **Configura las variables de entorno** en tu IDE:
```
DB_PASSWORD=tu_password_mysql
JWT_SECRET=tu_secreto_jwt
```

4. **Ejecuta el proyecto** — Flyway creará las tablas automáticamente.

5. **Inserta un usuario** para poder autenticarte:
```sql
INSERT INTO usuarios (nombre, correo_electronico, contrasena) 
VALUES ('Usuario', 'usuario@forohub.com', '$2a$10$...');
```

## 📡 Endpoints de la API

### 🔐 Autenticación

| Método | URL | Descripción |
|---|---|---|
| POST | `/login` | Genera token JWT |

**Body:**
```json
{
    "correoElectronico": "usuario@forohub.com",
    "contrasena": "tu_password"
}
```

**Respuesta:**
```json
{
    "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

### 📋 Tópicos (requieren Bearer Token)

| Método | URL | Descripción |
|---|---|---|
| POST | `/topicos` | Registrar tópico |
| GET | `/topicos` | Listar tópicos (paginado) |
| GET | `/topicos/{id}` | Detalle de tópico |
| PUT | `/topicos/{id}` | Actualizar tópico |
| DELETE | `/topicos/{id}` | Eliminar tópico |

**Body POST/PUT:**
```json
{
    "titulo": "Título del tópico",
    "mensaje": "Contenido del tópico",
    "autor": "Nombre del autor",
    "curso": "Nombre del curso"
}
```

## 📖 Documentación Swagger

La API cuenta con documentación automática generada con SpringDoc OpenAPI.

Accede en: **http://localhost:8080/swagger-ui/index.html**

![Swagger UI](https://img.shields.io/badge/Swagger-UI-85EA2D?style=for-the-badge&logo=swagger)

## 🔒 Seguridad

- Autenticación stateless con JWT
- Contraseñas encriptadas con BCrypt
- Credenciales sensibles gestionadas con variables de entorno
- Validación de token en cada request mediante filtro personalizado

## 👨‍💻 Autor

**Gonzalo** — Pasante en equipo de Data Engineering & ETL  
GitHub: [github.com/Gonzalo8707](https://github.com/Gonzalo8707)

---

*Desarrollado como parte del programa Oracle ONE + Alura Latam — Challenge Backend*
