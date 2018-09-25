package com.ensat.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.ensat.entities.FileInfo;
import com.ensat.entities.ImageOrder;
import com.ensat.entities.User;
import com.ensat.services.FileStorage;
import com.ensat.services.OrderService;
import com.ensat.services.UserService;

@Controller
public class DownloadFileController {

	@Autowired
	FileStorage fileStorage;
	
	@Autowired
	UserService userService;
	
	@Autowired
	OrderService orderService;
	
	/*
	 * Retrieve Files' Information
	 */
	@GetMapping("/files")
	public String getListFiles(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User u = userService.findUserByEmail(auth.getName());
		String name = u.getFirstname() + " " + u.getLastname();
		
		List<FileInfo> fileInfos = fileStorage.loadFiles(name).map(
					path ->	{
						String filename = path.getFileName().toString();
						String url = MvcUriComponentsBuilder.fromMethodName(DownloadFileController.class,
		                        "downloadFile", path.getFileName().toString()).build().toString();
						return new FileInfo(filename, url); 
					} 
				)
				.collect(Collectors.toList());
		
		model.addAttribute("files", fileInfos);
		
		Iterable<ImageOrder> lists = orderService.getDistinctOrder();
		model.addAttribute("lists", lists);
		return "listfiles";
	} 
 
    /*
     * Download Files
     */
	@GetMapping("/files/{filename}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
		Resource file = fileStorage.loadFile(filename);
		return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
					.body(file);	
	}
}