package pl.sztukakodu.TaskTreeApp.tasks.bounduary;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

public interface StorageService {

    void saveFile(Long taskId, MultipartFile file) throws IOException;

    Resource loadFile(String filename) throws MalformedURLException;
}
