package mx.tecmilenio.citas.persistence;

import mx.tecmilenio.citas.domain.Doctor;

import java.util.ArrayList;
import java.util.List;

public class DoctorRepositoryCsv implements Repository<Doctor> {
    private final CsvFileManager fm;
    private final String file;

    public DoctorRepositoryCsv(CsvFileManager fm, String file) {
        this.fm = fm;
        this.file = file;
    }

    @Override
    public void save(Doctor d) throws Exception {
        fm.appendLine(file, d.getId() + "," + escape(d.getNombreCompleto()) + "," + escape(d.getEspecialidad()));
    }

    @Override
    public List<Doctor> findAll() throws Exception {
        List<String> lines = fm.readLines(file);
        List<Doctor> out = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            if (line.isEmpty()) continue;
            if (i == 0 && line.toLowerCase().startsWith("id,")) continue;
            String[] p = line.split(",", -1);
            if (p.length < 3) continue;
            out.add(new Doctor(p[0], p[1], p[2]));
        }
        return out;
    }

    @Override
    public boolean existsById(String id) throws Exception {
        for (Doctor d : findAll()) {
            if (d.getId().equalsIgnoreCase(id)) return true;
        }
        return false;
    }

    private String escape(String s) { return s.replace(",", " "); }
}
