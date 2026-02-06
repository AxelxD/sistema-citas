package mx.tecmilenio.citas;

import mx.tecmilenio.citas.persistence.*;
import mx.tecmilenio.citas.service.AuthService;
import mx.tecmilenio.citas.service.ClinicService;
import mx.tecmilenio.citas.util.InputUtil;

import java.nio.file.Path;
import java.nio.file.Paths;

public class App {

    public static void main(String[] args) {
        Path base = Paths.get("db");
        String adminsFile   = base.resolve("admins.csv").toString();
        String doctorsFile  = base.resolve("doctores.csv").toString();
        String patientsFile = base.resolve("pacientes.csv").toString();
        String apptsFile    = base.resolve("citas.csv").toString();

        CsvFileManager fm = new CsvFileManager();

        try {
            fm.ensureCsvWithHeader(adminsFile, "id,passwordHash");
            fm.ensureCsvWithHeader(doctorsFile, "id,nombreCompleto,especialidad");
            fm.ensureCsvWithHeader(patientsFile, "id,nombreCompleto");
            fm.ensureCsvWithHeader(apptsFile, "id,fechaHora,motivo,idDoctor,idPaciente");
        } catch (Exception e) {
            System.out.println("Error inicializando db/: " + e.getMessage());
            System.out.println("El sistema continuará, pero revisa permisos/rutas.");
        }

        AdminRepositoryCsv adminRepo = new AdminRepositoryCsv(fm, adminsFile);
        DoctorRepositoryCsv doctorRepo = new DoctorRepositoryCsv(fm, doctorsFile);
        PacienteRepositoryCsv pacienteRepo = new PacienteRepositoryCsv(fm, patientsFile);
        CitaRepositoryCsv citaRepo = new CitaRepositoryCsv(fm, apptsFile);

        AuthService auth = new AuthService(adminRepo, fm, adminsFile);
        ClinicService clinic = new ClinicService(doctorRepo, pacienteRepo, citaRepo);

        System.out.println("=== Sistema de Administración de Citas (Java 25) ===");
        System.out.println("Datos locales: db/ (CSV).\n");

        while (true) {
            try {
                String id = InputUtil.readNonEmpty("Admin ID: ");
                String pass = InputUtil.readNonEmpty("Contraseña: ");

                if (auth.login(id, pass)) {
                    System.out.println("Acceso permitido.\n");
                    break;
                } else {
                    System.out.println("Credenciales inválidas. Intenta de nuevo.\n");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("El sistema continuará.\n");
            }
        }

        int option = -1;
        while (option != 7) {
            try {
                System.out.println("Menú:");
                System.out.println("1) Alta Doctor");
                System.out.println("2) Alta Paciente");
                System.out.println("3) Crear Cita");
                System.out.println("4) Listar Doctores");
                System.out.println("5) Listar Pacientes");
                System.out.println("6) Listar Citas");
                System.out.println("7) Salir");
                option = InputUtil.readInt("Elige opción: ");

                switch (option) {
                    case 1 -> clinic.altaDoctor();
                    case 7 -> System.out.println("Saliendo... ¡hasta luego!");
                    default -> System.out.println("Opción inválida.");
                }

                System.out.println();
            } catch (Exception e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
                System.out.println("El sistema continuará ejecutándose.\n");
            }
        }
    }
}
