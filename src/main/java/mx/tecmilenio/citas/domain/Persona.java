package mx.tecmilenio.citas.domain;

public abstract class Persona {
    private final String id;
    private final String nombreCompleto;

    protected Persona(String id, String nombreCompleto) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
    }

    public String getId() { return id; }
    public String getNombreCompleto() { return nombreCompleto; }
}
