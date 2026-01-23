package mx.tecmilenio.citas.domain;

public class Doctor extends Persona {
    private final String especialidad;

    public Doctor(String id, String nombreCompleto, String especialidad) {
        super(id, nombreCompleto);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() { return especialidad; }

    @Override
    public String toString() {
        return getId() + " | " + getNombreCompleto() + " | " + especialidad;
    }
}
