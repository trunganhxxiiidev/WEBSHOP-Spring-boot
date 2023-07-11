package ta.webshop.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Service;
import jakarta.servlet.ServletContext;

public class UploadServiceImpl implements UploadService{
	@Autowired
	ServletContext app;
	
	@Override
	public File save(MultipartFile file, String folder) {
		File dir = new File(app.getRealPath(folder));
		if(!dir.exists()) {
			dir.mkdirs();
		}
		try {
			File newFile = new File(dir, file.getOriginalFilename());
			file.transferTo(newFile);
			return newFile;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public byte[] read(String path) {
		String readPath = app.getRealPath(path);
		try {
			return Files.readAllBytes(Paths.get(readPath));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(String path) {
		String readPath = app.getRealPath(path);
		new File(readPath).delete();
	}

}
