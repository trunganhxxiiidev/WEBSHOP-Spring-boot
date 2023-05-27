package ta.webshop.controller;


import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import ta.webshop.jpa.entity.Product;
import ta.webshop.jpa.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;
	
	@RequestMapping("/product/category/{id}")
	public String findCategoryById(Model model,@PathVariable("id") Integer id,
			@RequestParam("p") Optional<Integer> popt) {
		Integer p=popt.orElse(0);
		Pageable pageable=PageRequest.of(p, 6);
		Page<Product> page=productService.findAllByCategoryId(id,pageable);
		model.addAttribute("itemsOfPage", page);
		return "product/list";
	}
	
	@RequestMapping("/product/search")
	public String findByName(Model model,
			@RequestParam("keyword") String keyword,
			@RequestParam("p") Optional<Integer> popt) {
		Integer p=popt.orElse(0);
		Pageable pageable=PageRequest.of(p, 6);
		Page<Product> page = productService.findByName(keyword,pageable);
		model.addAttribute("itemsOfPage", page);
		return "product/list";
	}
	
	@RequestMapping("/product/discount")
	public String findByDiscount(Model model,@RequestParam("p") Optional<Integer> popt) {
		Integer p=popt.orElse(0);
		Pageable pageable=PageRequest.of(p, 6);
		Page<Product> page = productService.findByDiscount(pageable);
		model.addAttribute("itemsOfPage", page);
		return "product/list";
	}
	
	@RequestMapping("/product/best-seller")
	public String findByBest(Model model,@RequestParam("p") Optional<Integer> popt) {
		Integer p=popt.orElse(0);
		Pageable pageable=PageRequest.of(p, 6);
		Page<Product> page = productService.findByBest( pageable);
		model.addAttribute("itemsOfPage", page);
		return "product/list";
	}

	@RequestMapping("/product/likes")
	public String findByFavorite(Model model,@RequestParam("p") Optional<Integer> popt) {
		Integer p=popt.orElse(0);
		Pageable pageable=PageRequest.of(p, 6);
		Page<Product> page = productService.findByLikes( pageable);
		model.addAttribute("itemsOfPage", page);
		return "product/list";
	}
	
	@RequestMapping("/product/latest")
	public String findByLatest(Model model,@RequestParam("p") Optional<Integer> popt) {
		Integer p=popt.orElse(0);
		Pageable pageable=PageRequest.of(p, 6);
		Page<Product> page = productService.findByLatest( pageable);
		model.addAttribute("itemsOfPage", page);
		return "product/list";
	}
	
	@RequestMapping("product/detail/{id}")
	public String Detail(Model model, @PathVariable("id") Integer id ) {
		Product product=productService.findById(id);
		model.addAttribute("item", product);
		return "product/detail";
	}
	
	
	//rest api 
	
	@ResponseBody
	@RequestMapping("product/like/{id}")
	public Object like(@PathVariable("id") Integer id ) {
		Product product=productService.findById(id);
		product.setLikeCount(product.getLikeCount()+1);
		productService.update(product);
		return product.getLikeCount();
	}
	
	
	
}
