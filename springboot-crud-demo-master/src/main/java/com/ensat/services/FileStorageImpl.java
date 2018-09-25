package com.ensat.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageImpl implements FileStorage {

	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	private final Path rootLocation = null;

	@Override
	public void store(String name, MultipartFile[] files) {
		for (MultipartFile file : files) {
			try {
				File theDir = new File(name);
				if (!theDir.exists()) {
					try {
						theDir.mkdir();
					} catch (SecurityException se) {
						// handle it
					}
				}
				Path root = Paths.get(theDir.getName());
				Files.copy(file.getInputStream(), root.resolve(file.getOriginalFilename()));
			} catch (Exception e) {
				throw new RuntimeException("FAIL! -> message = " + e.getMessage());
			}
		}
	}

	@Override
	public Resource loadFile(String filename) {
		try {
			Path file = rootLocation.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("FAIL!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Error! -> message = " + e.getMessage());
		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	@Override
	public void init() {
		try {
			Files.createDirectory(rootLocation);
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize storage!");
		}
	}

	@Override
	public Stream<Path> loadFiles(String name) {
		Path root = Paths.get(name);
		try {
			return Files.walk(root, 1).filter(path -> !path.equals(root))
					.map(root::relativize);
		} catch (IOException e) {
			throw new RuntimeException("\"Failed to read stored file ");
		}
	}
}
