# Sistema de Administración de Citas (Java 11 + Maven)

Proyecto base para simular un sistema de administración de citas para un consultorio clínico.

## Instalación y configuración
**Requisitos:**
- Java JDK 11
- Maven
- IntelliJ IDEA (opcional)

Clona el repositorio y abre el proyecto en IntelliJ IDEA.

## Uso del programa

### 1) Compilar (FAT JAR)
En la raíz del proyecto:

```bash
mvn clean package
```

### 2) Ejecutar
```bash
java -jar target/sistema-citas-1.0.0.jar
```

### Credenciales iniciales
- **Admin ID:** `admin`
- **Contraseña:** `admin123`

> El archivo de admins se encuentra en: `data/admins.csv`

## Datos (CSV)
Los archivos CSV se guardan en la carpeta `data/`:
- `admins.csv`
- `doctores.csv`
- `pacientes.csv`
- `citas.csv`

## Créditos
Proyecto elaborado como evidencia académica para la materia **Programación en Java**.

## Licencia
MIT
