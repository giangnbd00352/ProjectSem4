package com.ensat.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ensat.entities.ImageOrder;
import com.ensat.entities.User;
import com.ensat.services.FileStorage;
import com.ensat.services.OrderService;
import com.ensat.services.UserService;

@Controller
public class UploadFileController {

	@Autowired
	FileStorage fileStorage;

	@Autowired
	OrderService orderService;

	@Autowired
	private UserService userService;

	// @PostMapping("/upload")
	// public String uploadMultipartFile(@RequestParam("uploadfile") MultipartFile[]
	// files,@RequestParam("name") String name, Model model) {
	// try {
	// fileStorage.store(name, files);
	// model.addAttribute("message", "File uploaded successfully!");
	// } catch (Exception e) {
	// model.addAttribute("message", "Fail!");
	// }
	// return "home";
	// }

	@PostMapping("/upload")
	public String uploadMultipartFile(@RequestParam("uploadfile") MultipartFile[] files,
			@RequestParam("name") String name, @RequestParam("material") String material,
			@RequestParam("size") String size, @RequestParam("quantity") String quantity,
			@RequestParam("city") String city, @RequestParam("address") String address,
			@RequestParam("phone") String phone, @RequestParam("email") String email, Model model) {
		ImageOrder order;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		long millis = System.currentTimeMillis() % 1000000000;
		String orderCode = "order" + millis;
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		String date = dtf.format(localDate);

		model.addAttribute("userName", user.getFirstname() + " " + user.getLastname());
			try {
				for (MultipartFile file : files) {
					order = new ImageOrder();
					order.setOrderCode(orderCode);
					order.setDateOrder(date);
					order.setStatus("receive");
					order.setUsername(name);
					order.setMaterial(material);
					order.setSize(size);
					order.setQuantity(Integer.parseInt(quantity));
					order.setCity(city);
					order.setAddressDetail(address);
					order.setPhone(phone);
					order.setEmail(email);
					order.setImageName(file.getOriginalFilename());
				System.out.println("name: " + file.getOriginalFilename());				
				orderService.saveOrder(order);
				}
				fileStorage.store(name, files);
				String content = "<html> <b>Order</b> <br/>Customer name: " + name + "<br/>Material: " + material
						+ "<br/>Size: " + size + "<br/>Amount: " + quantity + "<br/>Address: " + address + ", " + city
						+ "<br/>Phone: " + phone + "</html>";
				Send(content, email);
				model.addAttribute("message", "File uploaded successfully!");
			} catch (Exception e) {
				model.addAttribute("message", "Fail!");
			}			
		return"home";

	}

	public static void Send(String content, String email) throws AddressException, MessagingException {
		Properties pro = new Properties();
		pro.put("mail.smtp.auth", "true");
		pro.put("mail.smtp.starttls.enable", "true");
		pro.put("mail.smtp.host", "smtp.gmail.com");
		pro.put("mail.smtp.port", 587);
		pro.put("mail.smtp.sockerFactory.class", javax.net.ssl.SSLSocketFactory.class.getName());

		Session session = Session.getDefaultInstance(pro, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("javafeedbackc16081@gmail.com", "abc123xyz");
			}

		});

		Message message = new MimeMessage(session);
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
		message.addRecipient(Message.RecipientType.CC, new InternetAddress("giangnbd00352@fpt.edu.vn"));
		message.setSubject("Image Printing");
		message.setContent(content, "text/html");

		Transport.send(message);
	}

}
