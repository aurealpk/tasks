package pl.sztukakodu.TaskTreeApp.tasks.bounduary;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;

public class FileSystemStorageService implements StorageService {
    private final Path path;

    public FileSystemStorageService(Path path) {
        this.path = path;
    }

    @Override
    public void saveFile(Long taskId, MultipartFile file) throws IOException {
        Path targetPath = path.resolve(file.getName());
        Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
    }

    @Override
    public Resource loadFile(String filename) throws MalformedURLException {
        return new UrlResource((path.resolve(filename).toUri()));
     }

}
