package ta.webshop.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeAController {
	@ModelAttribute("requestURI")
	public String requestURI(final HttpServletRequest request) {
	   return request.getRequestURI();
	}
	@RequestMapping("/admin/home/index")
	public String index() {
		return "admin/home/index";
	}

}
