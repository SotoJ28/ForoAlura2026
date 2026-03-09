#**Foro API Alura 2026**

###**Arquitectura del proyecto**

com.alura.foro
│
├── controller
│   ├── AuthController
│   └── TopicoController
│
├── domain
│   ├── topico
│   │   ├── Topico
│   │   ├── TopicoRepository
│   │   ├── DTOs (DatosRegistroTopico, etc.)
│   │
│   └── usuario
│       ├── Usuario
│       └── UsuarioRepository
│
├── infra
│   └── security
│       ├── SecurityConfig
│       ├── JwtFilter
│       ├── TokenService
│       └── UserDetailsServiceImpl
│
└── ForoApplication

###**El proyecto implementa buenas prácticas como:**
Arquitectura por capas,
DTOs para intercambio de datos,
Seguridad con Spring Security,
Autenticación stateless con JWT,
Persistencia con JPA + PostgreSQL,
Migraciones de base de datos con Flyway,
Paginación de resultados,
Borrado lógico de registros.

###**Tecnologías utilizadas:**
Java 17,
Spring Boot,
Spring Security,
Spring Data JPA,
JWT (java-jwt),
PostgreSQL,
Flyway,
Maven,
Insomnia / Postman para pruebas.

###**Funcionalidades implementadas:**
Crear tópico,
Listar tópicos,
Ver tópico por ID,
Actualizar tópico,
Eliminar tópico (borrado lógico),
Autenticación JWT,
Validación de duplicados,
Paginación.
