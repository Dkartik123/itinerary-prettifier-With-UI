package itineraryprettifier.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileWriterService {

    /**
     * Записывает содержимое в указанный файл.
     * @param filePath путь к файлу для записи.
     * @param content строка, которую необходимо записать в файл.
     * @throws IOException если произошла ошибка при записи файла.
     */
    public void writeFile(String filePath, String content) throws IOException {
        Path path = Paths.get(filePath);
        Files.write(path, content.getBytes());
    }
}
