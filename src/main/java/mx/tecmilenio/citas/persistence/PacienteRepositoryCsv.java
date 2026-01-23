package mx.tecmilenio.citas.persistence;

import mx.tecmilenio.citas.domain.Paciente;

import java.util.ArrayList;
import java.util.List;

public class PacienteRepositoryCsv implements Repository<Paciente> {
    private final CsvFileManager fm;
    private final String file;

    public PacienteRepositoryCsv(CsvFileManager fm, String file) {
        this.fm = fm;
        this.file = file;
    }

    @Override
    public void save(Paciente p) throws Exception {
        fm.appendLine(file, p.getId() + "," + escape(p.getNombreCompleto()));
    }

    @Override
    public List<Paciente> findAll() throws Exception {
        List<String> lines = fm.readLines(file);
        List<Paciente> out = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            if (line.isEmpty()) continue;
            if (i == 0 && line.toLowerCase().startsWith("id,")) continue;
            String[] a = line.split(",", -1);
            if (a.length < 2) continue;
            out.add(new Paciente(a[0], a[1]));
        }
        return out;
    }

    @Override
    public boolean existsById(String id) throws Exception {
        for (Paciente p : findAll()) {
            if (p.getId().equalsIgnoreCase(id)) return true;
        }
        return false;
    }

    private String escape(String s) { return s.replace(",", " "); }
}
