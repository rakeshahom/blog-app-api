package com.genius.blog.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.genius.blog.services.FileService;
@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		
		String name = file.getOriginalFilename();
		String randomID = UUID.randomUUID().toString();
		String filename = randomID.concat(name.substring(name.lastIndexOf(".")));
		String fullPath = path + File.separator + filename;

		File file2 = new File(path);
		if (!file2.exists()) {
			file2.mkdir();
		}
		Files.copy(file.getInputStream(), Paths.get(fullPath));
		return filename;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {

		String fullPath = path + File.separator + fileName;
		InputStream is = new FileInputStream(fullPath);
		return is;
	}

}
