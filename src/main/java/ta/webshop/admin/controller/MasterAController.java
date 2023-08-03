package ta.webshop.admin.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import ta.webshop.jpa.entity.Role;
import ta.webshop.jpa.entity.User;
import ta.webshop.jpa.entity.UserRole;
import ta.webshop.jpa.service.CategoryService;
import ta.webshop.jpa.service.RoleService;
import ta.webshop.jpa.service.UserService;
import ta.webshop.service.SessionService;
import ta.webshop.service.UploadService;

@Controller("masterCtrl")
public class MasterAController {
	@Autowired
	UserService userService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	UploadService uploadService;
	@Autowired
	SessionService sessionService;
	@Autowired
	RoleService roleService;
	@Autowired
	PasswordEncoder pe;
	@ModelAttribute("requestURI")
	public String requestURI(final HttpServletRequest request) {
	   return request.getRequestURI();
	}
	Page<User> getPage(){
		return userService.findMasters(Pageable.unpaged());
	}
	
	@RequestMapping("/admin/master/index")
	public String index(Model model) {
		Page<User> page = this.getPage();
		
		model.addAttribute("form", new User());
		model.addAttribute("page", page);
		return "admin/master/index";
	}
	@RequestMapping("/admin/master/edit/{username}")
	public String edit(Model model, @PathVariable("username") String username) {
		User user = userService.findByUsername(username);
		Page<User> page = this.getPage();
		model.addAttribute("form", user);
		model.addAttribute("page", page);
		return "admin/master/index";
	}
	@RequestMapping("/admin/master/delete/{username}")
	public String delete(Model model, @PathVariable("username") String username) {
		userService.deleteByUsername(username);
		Page<User> page = this.getPage();
		model.addAttribute("form", new User());
		model.addAttribute("page", page);
		return "admin/master/index";
	}
	
	@RequestMapping("/admin/master/create")
	public String create(Model model, User entity, 
			@RequestPart("photo_file") MultipartFile file,
			@RequestParam("roleIds") List<String> roleIds,
			@RequestParam("passview") String passview) {
		if(!file.isEmpty()) {
			File photo = uploadService.save(file, "/images/photos");
			entity.setPhoto(photo.getName());
		} else {
			entity.setPhoto("new.png");
		}
		entity.setPassword(pe.encode(passview));
		userService.create(entity, roleIds);
		Page<User> page = this.getPage();
		model.addAttribute("form", new User());
		model.addAttribute("page", page);
		return "admin/master/index";
	}
	
	@RequestMapping("/admin/master/update")
	public String update(Model model, 
			@ModelAttribute("form") User entity, 
			@RequestPart("photo_file") MultipartFile file,
			@RequestParam("roleIds") List<String> roleIds,
			@RequestParam("passview") String passview) {
		if(!file.isEmpty()) {
			File photo = uploadService.save(file, "/images/photos");
			entity.setPhoto(photo.getName());
		} 
		if(!passview.equals("??????")) {
			entity.setPassword(pe.encode(passview));
		}
		userService.update(entity, roleIds);
		Page<User> page = this.getPage();
		model.addAttribute("page", page);
		return "admin/master/index";
	}
	
	@ModelAttribute("roles")
	public List<Role> getRoles(){
		return roleService.findAll();
	}
	
	public boolean isInRole(User user, Role role) {
		List<UserRole> userRoles = user.getUserRoles();
		if(userRoles == null) {
			return false;
		}
		return userRoles.stream()
				.anyMatch(ur -> ur.getRole().getId().equals(role.getId()));
	}
}
