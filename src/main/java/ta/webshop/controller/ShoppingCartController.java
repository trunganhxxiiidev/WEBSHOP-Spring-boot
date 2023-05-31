package ta.webshop.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ta.webshop.jpa.service.ShoppingCartService;

@Controller
public class ShoppingCartController {
	@Autowired
	ShoppingCartService cartService;
	
	@ResponseBody
	@RequestMapping("/api/cart/info")
	public Object getInfo(){
		return Map.of("count",cartService.getCount(),"amount",cartService.getAmount());
	}
	
	@ResponseBody
	@RequestMapping("/api/cart/add/{id}")
	public Object add (@PathVariable("id") Integer id){
		cartService.add(id);
		return this.getInfo();
	}
	
	@RequestMapping("/cart/view")
	public String view(Model model) {
		model.addAttribute("cart", cartService);
		
		return "cart/view";
	}
	
	@ResponseBody
	@RequestMapping("/api/cart/remove/{id}")
	public Object remove(@PathVariable("id") Integer id){
		cartService.remove(id);
		return this.getInfo();
	}
	
	@RequestMapping("/cart/clear")
	public String cart(Model model) {
		cartService.clear(); 
		return "forward:/cart/view";
	}
	
	@ResponseBody
	@RequestMapping("/api/cart/update/{id}/{qty}")
	public Object update(@PathVariable("id") Integer id,@PathVariable("qty") Integer qty){
		cartService.update(id, qty);   
		return this.getInfo();
	}
}
