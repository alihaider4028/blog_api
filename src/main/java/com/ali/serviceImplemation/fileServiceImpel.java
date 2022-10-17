package com.ali.serviceImplemation;

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

import com.ali.services.fileService;
@Service
public class fileServiceImpel implements fileService{

	@Override
	public String uploadimage(String path, MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
	String name =file.getOriginalFilename();
	
	
	String randomID =UUID.randomUUID().toString();
	String randomFileName =randomID+ name.substring(name.lastIndexOf('.'));	
	String filePath =path+File.separator+randomFileName;

	
	File f = new File(path);
		if(!f.exists()) {
			f.mkdir();
			
			
		}
		
		Files.copy(file.getInputStream(), Paths.get(filePath));
		
		
		
		return randomFileName;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		String fullpath = path+File.separator+fileName;
		InputStream  is = new FileInputStream(fullpath);
		// TODO Auto-generated method stub
		return is;
	}

}
