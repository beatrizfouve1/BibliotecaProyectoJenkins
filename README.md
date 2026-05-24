# 📚 Biblioteca - Sistema de Gestión

![Jenkins Build Status](http://34.229.96.140:8080/buildStatus/icon?job=Biblioteca-Pipeline1)

## 📖 Descripción

Sistema de gestión de biblioteca desarrollado en Java que permite:

- ✅ Gestionar catálogo de libros
- ✅ Gestionar socios de la biblioteca
- ✅ Realizar préstamos (máximo 3 libros por socio)
- ✅ Realizar devoluciones
- ✅ Reservar libros no disponibles
- ✅ Control de disponibilidad de libros

## 🛠️ Tecnologías utilizadas

| Tecnología | Versión | Propósito |
|------------|---------|-----------|
| Java | 17 | Lenguaje de programación |
| Maven | 3.8.7 | Gestión de dependencias y build |
| JUnit | 5.10.0 | Pruebas unitarias |
| Jenkins | 2.4xx | Integración continua (CI) |
| SonarQube | 10.3.0 | Análisis de calidad de código |
| Git | - | Control de versiones |

## 🚀 Pipeline CI/CD

El pipeline de Jenkins consta de las siguientes etapas:

| Etapa | Comando | Estado |
|-------|---------|--------|
| 1. Clonar Código | `git checkout` | ✅ |
| 2. Compilar Código | `mvn clean compile` | ✅ |
| 3. Ejecutar Pruebas | `mvn test` | ✅ |
| 4. Análisis SonarQube | `mvn sonar:sonar` | ✅ |
| 5. Empaquetar Aplicación | `mvn package` | ✅ |
| 6. Desplegar Artefacto | `cp target/*.jar /tmp/` | ✅ |

**Badge de estado del pipeline:**
![Jenkins Build Status](http://34.229.96.140:8080/buildStatus/icon?job=Biblioteca-Pipeline1)

## 📊 Calidad de código (SonarQube)

| Métrica | Estado |
|---------|--------|
| Quality Gate | ✅ PASSED |
| Bugs | 0 |
| Vulnerabilities | 0 |
| Code Smells | 3 |
| Coverage | 75% |

## 📁 Estructura del proyecto
BibliotecaProyectoJenkins/
├── Jenkinsfile # Pipeline declarativo
├── pom.xml # Configuración Maven
├── README.md # Este archivo
├── .gitignore
└── src/
├── main/java/org/example/
│ ├── Libro.java
│ ├── Main.java
│ ├── Reserva.java
│ └── Socio.java
└── test/java/org/example/
└── LibroTest.java

## 🔧 Cómo ejecutar el proyecto

### Requisitos previos
- Java 17
- Maven 3.8.7

### Pasos

```bash
# Clonar el repositorio
git clone https://github.com/beatrizfouve1/BibliotecaProyectoJenkins.git

# Entrar al directorio
cd BibliotecaProyectoJenkins

# Compilar el proyecto
mvn clean compile

# Ejecutar pruebas
mvn test

# Generar el JAR
mvn package

# Ejecutar la aplicación
java -jar target/Biblioteca-1.0-SNAPSHOT.jar

