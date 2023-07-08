package ta.webshop.controller;

import java.time.LocalDateTime;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import ta.webshop.jpa.entity.Order;
import ta.webshop.jpa.service.OrderService;
import ta.webshop.jpa.service.OrderStateService;
import ta.webshop.jpa.service.ProductService;
import ta.webshop.jpa.service.ShoppingCartService;
import ta.webshop.security.AuthService;

@Controller
public class OrderController {
	@Autowired
	ShoppingCartService shoppingCartService;
	@Autowired
	AuthService authService;
	@Autowired
	OrderService orderService;
	@Autowired
	OrderStateService stateService;
	@Autowired
	ProductService productService;
	
	
	@RequestMapping("order/checkout")
	public String checkout(Model model){
		Order order=new Order();
		order.setCreateDate(new Date());
		order.setAmount(shoppingCartService.getAmount());
		order.setCustomer(authService.getUser());
		order.setOrderState(stateService.findById(0));
		model.addAttribute("order",order);
		model.addAttribute("cart",shoppingCartService);
		return "order/checkout";
	}
	
	@RequestMapping("/order/purchase")
	public String purchase(Model model,
			@ModelAttribute("order") Order order) {
		try {
			orderService.purchase(order, shoppingCartService.getItems());
			model.addAttribute("cart",shoppingCartService);
			shoppingCartService.clear();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "order/checkout";
	}
}
