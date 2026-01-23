package mx.tecmilenio.citas.persistence;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class CsvFileManager {

    public List<String> readLines(String filePath) throws IOException {
        Path p = Paths.get(filePath);
        if (!Files.exists(p)) {
            Files.createDirectories(p.getParent());
            Files.createFile(p);
            return new ArrayList<>();
        }
        return Files.readAllLines(p, StandardCharsets.UTF_8);
    }

    public void appendLine(String filePath, String line) throws IOException {
        Path p = Paths.get(filePath);
        if (!Files.exists(p)) {
            Files.createDirectories(p.getParent());
            Files.createFile(p);
        }
        try (BufferedWriter bw = Files.newBufferedWriter(p, StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
            bw.write(line);
            bw.newLine();
        }
    }
}
