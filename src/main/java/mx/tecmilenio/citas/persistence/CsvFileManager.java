package mx.tecmilenio.citas.persistence;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class CsvFileManager {

    public void ensureCsvWithHeader(String filePath, String header) throws IOException {
        Path p = Paths.get(filePath);
        if (!Files.exists(p)) {
            Files.createDirectories(p.getParent());
            Files.createFile(p);
            try (BufferedWriter bw = Files.newBufferedWriter(p, StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
                bw.write(header);
                bw.newLine();
            }
            return;
        }

        if (Files.size(p) == 0) {
            try (BufferedWriter bw = Files.newBufferedWriter(p, StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
                bw.write(header);
                bw.newLine();
            }
        }
    }

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
