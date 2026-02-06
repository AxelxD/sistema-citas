package mx.tecmilenio.citas.service;

import mx.tecmilenio.citas.domain.Cita;
import mx.tecmilenio.citas.domain.Doctor;
import mx.tecmilenio.citas.domain.Paciente;
import mx.tecmilenio.citas.persistence.CitaRepositoryCsv;
import mx.tecmilenio.citas.persistence.DoctorRepositoryCsv;
import mx.tecmilenio.citas.persistence.PacienteRepositoryCsv;
import mx.tecmilenio.citas.util.InputUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ClinicService {

    private final DoctorRepositoryCsv doctorRepo;
    private final PacienteRepositoryCsv pacienteRepo;
    private final CitaRepositoryCsv citaRepo;

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public ClinicService(DoctorRepositoryCsv doctorRepo, PacienteRepositoryCsv pacienteRepo, CitaRepositoryCsv citaRepo) {
        this.doctorRepo = doctorRepo;
        this.pacienteRepo = pacienteRepo;
        this.citaRepo = citaRepo;
    }

    public void altaDoctor() throws Exception {
        String id = InputUtil.readNonEmpty("ID Doctor: ");
        if (doctorRepo.existsById(id)) throw new IllegalArgumentException("El ID de doctor ya existe.");

        String nombre = InputUtil.readNonEmpty("Nombre completo: ");
        String esp = InputUtil.readNonEmpty("Especialidad: ");

        doctorRepo.save(new Doctor(id, nombre, esp));
        System.out.println("Doctor registrado correctamente.");
    }

    public void altaPaciente() throws Exception {
        String id = InputUtil.readNonEmpty("ID Paciente: ");
        if (pacienteRepo.existsById(id)) throw new IllegalArgumentException("El ID de paciente ya existe.");

        String nombre = InputUtil.readNonEmpty("Nombre completo: ");
        pacienteRepo.save(new Paciente(id, nombre));
        System.out.println("Paciente registrado correctamente.");
    }

    public void crearCita() throws Exception {
        String id = InputUtil.readNonEmpty("ID Cita: ");
        if (citaRepo.existsById(id)) throw new IllegalArgumentException("El ID de cita ya existe.");

        String fechaHoraStr = InputUtil.readNonEmpty("Fecha y hora (yyyy-MM-dd HH:mm): ");
        LocalDateTime fechaHora;
        try {
            fechaHora = LocalDateTime.parse(fechaHoraStr, dtf);
        } catch (Exception e) {
            throw new IllegalArgumentException("Formato de fecha/hora inválido. Usa: yyyy-MM-dd HH:mm");
        }

        String motivo = InputUtil.readNonEmpty("Motivo: ");
        String idDoctor = InputUtil.readNonEmpty("ID Doctor existente: ");
        String idPaciente = InputUtil.readNonEmpty("ID Paciente existente: ");

        if (!doctorRepo.existsById(idDoctor)) throw new IllegalArgumentException("No existe el doctor con ID: " + idDoctor);
        if (!pacienteRepo.existsById(idPaciente)) throw new IllegalArgumentException("No existe el paciente con ID: " + idPaciente);

        citaRepo.save(new Cita(id, fechaHora, motivo, idDoctor, idPaciente));
        System.out.println("Cita creada correctamente.");
    }

    public void listarDoctores() throws Exception {
        List<Doctor> list = doctorRepo.findAll();
        System.out.println("=== Doctores ===");
        if (list.isEmpty()) System.out.println("(sin registros)");
        for (Doctor d : list) System.out.println(d);
    }

    public void listarPacientes() throws Exception {
        List<Paciente> list = pacienteRepo.findAll();
        System.out.println("=== Pacientes ===");
        if (list.isEmpty()) System.out.println("(sin registros)");
        for (Paciente p : list) System.out.println(p);
    }

    public void listarCitas() throws Exception {
        List<Cita> list = citaRepo.findAll();
        System.out.println("=== Citas ===");
        if (list.isEmpty()) System.out.println("(sin registros)");
        for (Cita c : list) System.out.println(c);
    }

}
