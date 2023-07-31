package ta.webshop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebSecurityController implements ErrorController {
	@RequestMapping("/security/login/form")
	public String login() {
		return "security/login";
	}
	
	@RequestMapping("/security/login/success")
	public String success(Model model) {
		model.addAttribute("message","đăng nhập thành công");
		return "forward:/security/login/form";
		
	}
	
	@RequestMapping({"/security/access/denied"})
	public String denied(Model model) {
		model.addAttribute("message","không có quyền truy cập");
		return "forward:/security/login/form";
		
	}
	
	@RequestMapping("/security/login/failure")
	public String failure(Model model) {
		model.addAttribute("message", "Sai thông tin đăng nhập");
		return "forward:/security/login/form";
	}
	
	@RequestMapping("/security/logout/success")
	public String logout(Model model) {
		model.addAttribute("message", "Đăng xuất thành công");
		return "forward:/security/login/form";
	}
	
	// @Autowired
	// AuthService authService;
	
	// @RequestMapping("/oauth2/login/success")
	// public String oauth2Success(Model model,OAuth2AuthenticationToken oauth2) {
	// 	authService.loginWithOAuth2(oauth2);
	// 	model.addAttribute("message","đăng nhập thành công");
	// 	return "forward:/security/login/form";
		
	// }
	

}
