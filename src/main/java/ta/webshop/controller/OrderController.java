package ta.webshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ta.webshop.jpa.entity.Order;
import ta.webshop.jpa.service.ShoppingCartService;
import ta.webshop.security.AuthService;

@Controller
public class OrderController {
	@Autowired
	ShoppingCartService shoppingCartService;
	@Autowired
	AuthService authService;
	
	@RequestMapping("order/checkout")
	public String checkout(Model model){
		Order order=new Order();
		model.addAttribute("order",order);
		return "order/checkout";
	}
}
