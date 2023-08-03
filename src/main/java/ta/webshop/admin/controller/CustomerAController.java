package ta.webshop.admin.controller;

import java.io.File;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import ta.webshop.jpa.entity.User;
import ta.webshop.jpa.service.CategoryService;
import ta.webshop.jpa.service.UserService;
import ta.webshop.service.SessionService;
import ta.webshop.service.UploadService;

@Controller
public class CustomerAController {
	@Autowired
	UserService userService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	UploadService uploadService;
	@Autowired
	SessionService sessionService;
	
	Page<User> getPage(){
		Pageable pageable = PageRequest.of(sessionService.get("p"), 8);
		return userService.findCustomers(pageable);
	}
	@ModelAttribute("requestURI")
	public String requestURI(final HttpServletRequest request) {
	   return request.getRequestURI();
	}
	
	@RequestMapping("/admin/customer/index")
	public String index(Model model, 
			@RequestParam("p") Optional<Integer> popt) {
		sessionService.set("p", popt.orElse(0));
		Page<User> page = this.getPage();
		
		model.addAttribute("form", new User());
		model.addAttribute("page", page);
		return "admin/customer/index";
	}
	@RequestMapping("/admin/customer/edit/{username}")
	public String edit(Model model, @PathVariable("username") String username) {
		User user = userService.findByUsername(username);
		Page<User> page = this.getPage();
		model.addAttribute("form", user);
		model.addAttribute("page", page);
		return "admin/customer/index";
	}
	@RequestMapping("/admin/customer/delete/{username}")
	public String delete(Model model, @PathVariable("username") String username) {
		userService.deleteByUsername(username);
		Page<User> page = this.getPage();
		model.addAttribute("form", new User());
		model.addAttribute("page", page);
		return "admin/customer/index";
	}
	@RequestMapping("/admin/customer/create")
	public String create(Model model, User entity, @RequestPart("photo_file") MultipartFile file) {
		if(!file.isEmpty()) {
			File photo = uploadService.save(file, "/images/photos");
			entity.setPhoto(photo.getName());
		} else {
			entity.setPhoto("new.png");
		}
		userService.create(entity);
		Page<User> page = this.getPage();
		model.addAttribute("form", new User());
		model.addAttribute("page", page);
		return "admin/customer/index";
	}
	@RequestMapping("/admin/customer/update")
	public String update(Model model, 
			@ModelAttribute("form") User entity, @RequestPart("photo_file") MultipartFile file) {
		if(!file.isEmpty()) {
			File photo = uploadService.save(file, "/images/photos");
			entity.setPhoto(photo.getName());
		} 
		userService.update(entity);
		Page<User> page = this.getPage();
		model.addAttribute("page", page);
		return "admin/customer/index";
	}
}
