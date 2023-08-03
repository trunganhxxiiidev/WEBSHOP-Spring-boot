package ta.webshop.admin.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import ta.webshop.jpa.entity.Category;
import ta.webshop.jpa.service.CategoryService;

@Controller
public class CategoryAController {
	@Autowired
	CategoryService categoryService;
	@ModelAttribute("requestURI")
	public String requestURI(final HttpServletRequest request) {
	   return request.getRequestURI();
	}
	@RequestMapping("/admin/category/index")
	public String index(Model model) {
		List<Category> list = categoryService.findAll();
		model.addAttribute("form", new Category());
		model.addAttribute("list", list);
		return "admin/category/index";
	}
	@RequestMapping("/admin/category/edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {
		Category category = categoryService.findById(id);
		List<Category> list = categoryService.findAll();
		model.addAttribute("form", category);
		model.addAttribute("list", list);
		return "admin/category/index";
	}
	@RequestMapping("/admin/category/delete/{id}")
	public String delete(Model model, @PathVariable("id") Integer id) {
		categoryService.deleteById(id);
		List<Category> list = categoryService.findAll();
		model.addAttribute("form", new Category());
		model.addAttribute("list", list);
		return "admin/category/index";
	}
	@RequestMapping("/admin/category/create")
	public String create(Model model, Category entity) {
		categoryService.create(entity);
		List<Category> list = categoryService.findAll();
		model.addAttribute("form", new Category());
		model.addAttribute("list", list);
		return "admin/category/index";
	}
	@RequestMapping("/admin/category/update")
	public String update(Model model, 
			@ModelAttribute("form") Category entity) {
		categoryService.update(entity);
		List<Category> list = categoryService.findAll();
		model.addAttribute("list", list);
		return "admin/category/index";
	}
}
