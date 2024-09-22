package itineraryprettifier.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileReaderService {

    /**
     * Читает содержимое файла по указанному пути.
     * @param filePath путь к файлу для чтения.
     * @return содержимое файла в виде строки.
     * @throws IOException если файл не найден или произошла ошибка при чтении.
     */
    public String readFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            throw new IOException("Input not found");
        }
        return new String(Files.readAllBytes(path));
    }
}
