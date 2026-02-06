# Sistema de Administración de Citas (Java 25 + Maven)

Aplicación de consola en Java para administrar **doctores**, **pacientes** y **citas** con **control de acceso** por administradores.
La información se almacena en archivos **CSV** dentro de la carpeta `db/`.

---

## Instalación y configuración

### Requisitos
- **JDK 25**
- **Maven 3.9+**
- (Opcional) IntelliJ IDEA

### Clonar y abrir
1. Clona el repositorio.
2. Abre el proyecto en IntelliJ IDEA (o cualquier IDE).
3. Verifica que el SDK del proyecto sea **Java 25**.

---

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

### Base de datos local (`db/`)
- La carpeta `db/` contiene los CSV del sistema.
- **No se suben al repositorio** (la carpeta incluye un `.gitignore` que ignora su contenido).
- Si el programa no encuentra los archivos, **los crea automáticamente** con sus encabezados.

Archivos generados:
- `db/admins.csv`  -> `id,passwordHash`
- `db/doctores.csv` -> `id,nombreCompleto,especialidad`
- `db/pacientes.csv` -> `id,nombreCompleto`
- `db/citas.csv` -> `id,fechaHora,motivo,idDoctor,idPaciente`

### Credenciales iniciales
Si `db/admins.csv` no existe o está vacío, el sistema crea un administrador por defecto:

- **Admin ID:** `admin`
- **Contraseña:** `admin123`

---

## Créditos
Evidencia académica para la materia **Programación en Java**.

## Licencia
MIT
