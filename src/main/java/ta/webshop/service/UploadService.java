package ta.webshop.service;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
	File save(MultipartFile file, String folder);
	byte[] read(String path);
	void delete(String path);
}
