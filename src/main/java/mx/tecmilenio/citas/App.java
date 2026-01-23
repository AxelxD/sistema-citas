package mx.tecmilenio.citas;

import mx.tecmilenio.citas.persistence.*;
import mx.tecmilenio.citas.service.AuthService;
import mx.tecmilenio.citas.service.ClinicService;
import mx.tecmilenio.citas.util.InputUtil;

import java.nio.file.Paths;

public class App {

    public static void main(String[] args) {
        String base = Paths.get("data").toString();
        String adminsFile   = Paths.get(base, "admins.csv").toString();
        String doctorsFile  = Paths.get(base, "doctores.csv").toString();
        String patientsFile = Paths.get(base, "pacientes.csv").toString();
        String apptsFile    = Paths.get(base, "citas.csv").toString();

        CsvFileManager fm = new CsvFileManager();

        AdminRepositoryCsv adminRepo = new AdminRepositoryCsv(fm, adminsFile);
        DoctorRepositoryCsv doctorRepo = new DoctorRepositoryCsv(fm, doctorsFile);
        PacienteRepositoryCsv pacienteRepo = new PacienteRepositoryCsv(fm, patientsFile);
        CitaRepositoryCsv citaRepo = new CitaRepositoryCsv(fm, apptsFile);

        AuthService auth = new AuthService(adminRepo);
        ClinicService clinic = new ClinicService(doctorRepo, pacienteRepo, citaRepo);

        System.out.println("=== Sistema de Administración de Citas (Java 11) ===");

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
                    case 2 -> clinic.altaPaciente();
                    case 3 -> clinic.crearCita();
                    case 4 -> clinic.listarDoctores();
                    case 5 -> clinic.listarPacientes();
                    case 6 -> clinic.listarCitas();
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
