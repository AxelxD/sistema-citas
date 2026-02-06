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

}
