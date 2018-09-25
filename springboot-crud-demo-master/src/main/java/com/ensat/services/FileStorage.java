package com.ensat.services;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorage {
	public void store(String name, MultipartFile[] files);
	public Resource loadFile(String filename);
	public void deleteAll();
	public void init();
	public Stream<Path> loadFiles(String name);
}
