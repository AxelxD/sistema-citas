package mx.tecmilenio.citas.domain;

public class Paciente extends Persona {
    public Paciente(String id, String nombreCompleto) {
        super(id, nombreCompleto);
    }

    @Override
    public String toString() {
        return getId() + " | " + getNombreCompleto();
    }
}
