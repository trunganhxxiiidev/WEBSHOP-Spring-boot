package ta.webshop.controller;

import java.io.File;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import ta.webshop.jpa.entity.User;
import ta.webshop.jpa.service.UserService;
import ta.webshop.security.AuthService;
import ta.webshop.service.MailService;
import ta.webshop.service.UploadService;

@Controller
public class AccountController {
	@Autowired
	UserService userService;
	@Autowired
	MailService mailService;
	@Autowired
	UploadService uploadService;
	@Autowired
	PasswordEncoder pe;
	@Autowired
	AuthService authService;
	
	
	@GetMapping("/account/signup")
	public String signUp(Model model) {
		model.addAttribute("user", new User());
		return "account/signup";
	}
	@PostMapping("/account/signup")
	public String signUp(Model model, 
			@ModelAttribute("user") User user,
			@RequestPart("photo_file") MultipartFile file) {
		if(!file.isEmpty()) {
			File photo = uploadService.save(file, "/images/photos");
			user.setPhoto(photo.getName());
		} else {
			user.setPhoto("new.png");
		}
		if(!userService.existByUsername(user.getUsername())) {
			user.setPassword(pe.encode(user.getPassword()));
			userService.create(user);
			mailService.sendWelcome(user);
			model.addAttribute("message", "Vui lòng kích hoạt tài khoản qua email");
			return "forward:/security/login/form";
		} else {
			model.addAttribute("message", "Username đã được người khác sử dụng");
		}
		return "account/signup";
	}

	@GetMapping("/account/activate/{username}")
	public String activate(Model model, 
			@PathVariable("username") String username) {
		User user = userService.findByUsername(username);
		
		user.setEnabled(true);
		if(user.getEnabled()==true) {
			userService.update(user);
			model.addAttribute("message", "Tài khoản đã được kích hoạt");
			return "forward:/security/login/form";
		}
		else {
			model.addAttribute("message", "kich hoat khong thanh cong");
			return "forward:/security/login/form";
		}
		
	}
	
	@GetMapping("/account/edit-profile")
	public String editProfile(Model model) {
		model.addAttribute("user", authService.getUser());
		return "account/edit-profile";
	}
	@PostMapping("/account/edit-profile")
	public String editProfile(Model model, 
			@ModelAttribute("user") User user,
			@RequestPart("photo_file") MultipartFile file) {
		if(!file.isEmpty()) {
			File photo = uploadService.save(file, "/images/photos");
			user.setPhoto(photo.getName());
		}
		userService.update(user);
		model.addAttribute("message", "Tài khoản đã được cập nhật");
		authService.setUser(user);
		return "account/edit-profile";
	}
	
	// @GetMapping("/account/change-password")
	// public String changePassword(Model model) {
	// 	return "account/change-password";
	// }
	// @PostMapping("/account/change-password")
	// public String changePassword(Model model, 
	// 		@RequestParam("username") String username,
	// 		@RequestParam("password") String password,
	// 		@RequestParam("newpass") String newpass,
	// 		@RequestParam("confirm") String confirm) {
	// 	User user = authService.getUser();
	// 	if(!user.getUsername().equalsIgnoreCase(username)) {
	// 		model.addAttribute("message", "Sai username");
	// 	} else if(!newpass.equals(confirm)) {
	// 		model.addAttribute("message", "Xác nhận mật khẩu mới không đúng");
	// 	} else if(!pe.matches(password, user.getPassword())) {
	// 		model.addAttribute("message", "Sai password");
	// 	} else {
	// 		user.setPassword(pe.encode(newpass));
	// 		userService.update(user);
	// 		model.addAttribute("message", "Đổi mật khẩu thành công");
	// 		authService.setUser(user);
	// 	}
	// 	return "account/change-password";
	// }
	
	// @GetMapping("/account/forgot-password")
	// public String forgotPassword(Model model) {
	// 	return "account/forgot-password";
	// }
	// @PostMapping("/account/forgot-password")
	// public String forgotPassword(Model model, 
	// 		@RequestParam("username") String username,
	// 		@RequestParam("email") String email) {
	// 	try {
	// 		User user = userService.findByUsername(username);
	// 		if(!user.getEmail().equalsIgnoreCase(email)) {
	// 			model.addAttribute("message", "Sai Email");
	// 		} else {
	// 			mailService.sendPasswordToken(user);
	// 			model.addAttribute("message", "Vui lòng reset password qua email");
	// 		}
	// 	} catch (Exception e) {
	// 		model.addAttribute("message", "Sai username");
	// 	}
		
	// 	return "account/forgot-password";
	// }
	
	// @GetMapping("/account/reset/{username}/{hash}")
	// public String resetPassword(Model model, 
	// 		@PathVariable("username") String username,
	// 		@PathVariable("hash") int hash) {
	// 	User user = userService.findByUsername(username);
	// 	if(hash == user.getPassword().hashCode()) {
	// 		return "account/reset-password";
	// 	}
	// 	return "account/forgot-password";
	// }
	
	// @PostMapping("/account/reset-password")
	// public String resetPassword(Model model, 
	// 		@RequestParam("username") String username,
	// 		@RequestParam("newpass") String newpass,
	// 		@RequestParam("confirm") String confirm) {
	// 	User user = userService.findByUsername(username);
	// 	if(!user.getUsername().equalsIgnoreCase(username)) {
	// 		model.addAttribute("message", "Sai username");
	// 	} else if(!newpass.equals(confirm)) {
	// 		model.addAttribute("message", "Xác nhận mật khẩu mới không đúng");
	// 	} else {
	// 		user.setPassword(pe.encode(newpass));
	// 		userService.update(user);
	// 		model.addAttribute("message", "Khởi tạo mật khẩu thành công");
	// 		return "forward:/security/login/form";
	// 	}
	// 	return "account/reset-password";
	// }
}
