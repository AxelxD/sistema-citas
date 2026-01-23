package mx.tecmilenio.citas.domain;

public class Admin {
    private final String id;
    private final String passwordHash;

    public Admin(String id, String passwordHash) {
        this.id = id;
        this.passwordHash = passwordHash;
    }

    public String getId() { return id; }
    public String getPasswordHash() { return passwordHash; }
}
