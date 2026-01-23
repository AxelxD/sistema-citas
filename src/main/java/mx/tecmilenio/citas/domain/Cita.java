package mx.tecmilenio.citas.domain;

import java.time.LocalDateTime;

public class Cita {
    private final String id;
    private final LocalDateTime fechaHora;
    private final String motivo;
    private final String idDoctor;
    private final String idPaciente;

    public Cita(String id, LocalDateTime fechaHora, String motivo, String idDoctor, String idPaciente) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.idDoctor = idDoctor;
        this.idPaciente = idPaciente;
    }

    public String getId() { return id; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public String getMotivo() { return motivo; }
    public String getIdDoctor() { return idDoctor; }
    public String getIdPaciente() { return idPaciente; }

    @Override
    public String toString() {
        return id + " | " + fechaHora + " | " + motivo + " | Doctor=" + idDoctor + " | Paciente=" + idPaciente;
    }
}
