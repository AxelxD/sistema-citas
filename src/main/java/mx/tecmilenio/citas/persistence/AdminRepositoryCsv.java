package mx.tecmilenio.citas.persistence;

import mx.tecmilenio.citas.domain.Admin;

import java.util.ArrayList;
import java.util.List;

public class AdminRepositoryCsv {
    private final CsvFileManager fm;
    private final String file;

    public AdminRepositoryCsv(CsvFileManager fm, String file) {
        this.fm = fm;
        this.file = file;
    }

    public List<Admin> findAll() throws Exception {
        List<String> lines = fm.readLines(file);
        List<Admin> out = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            if (line.isEmpty()) continue;
            if (i == 0 && line.toLowerCase().startsWith("id,")) continue;
            String[] a = line.split(",", -1);
            if (a.length < 2) continue;
            out.add(new Admin(a[0], a[1]));
        }
        return out;
    }
}
