package mx.tecmilenio.citas.persistence;

import mx.tecmilenio.citas.domain.Cita;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CitaRepositoryCsv implements Repository<Cita> {
    private final CsvFileManager fm;
    private final String file;

    public CitaRepositoryCsv(CsvFileManager fm, String file) {
        this.fm = fm;
        this.file = file;
    }

    @Override
    public void save(Cita c) throws Exception {
        fm.appendLine(file,
                c.getId() + "," +
                c.getFechaHora() + "," +
                escape(c.getMotivo()) + "," +
                c.getIdDoctor() + "," +
                c.getIdPaciente()
        );
    }

    @Override
    public List<Cita> findAll() throws Exception {
        List<String> lines = fm.readLines(file);
        List<Cita> out = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            if (line.isEmpty()) continue;
            if (i == 0 && line.toLowerCase().startsWith("id,")) continue;

            String[] a = line.split(",", -1);
            if (a.length < 5) continue;

            LocalDateTime dt = LocalDateTime.parse(a[1]);
            out.add(new Cita(a[0], dt, a[2], a[3], a[4]));
        }
        return out;
    }

    @Override
    public boolean existsById(String id) throws Exception {
        for (Cita c : findAll()) {
            if (c.getId().equalsIgnoreCase(id)) return true;
        }
        return false;
    }

    private String escape(String s) { return s.replace(",", " "); }
}
