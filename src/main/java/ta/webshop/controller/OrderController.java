package ta.webshop.controller;

import java.time.LocalDateTime;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ta.webshop.jpa.entity.Order;
import ta.webshop.jpa.entity.Product;
import ta.webshop.jpa.entity.User;
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
	
	@RequestMapping("/order/list")
	public String list(Model model) {
		User user = authService.getUser();
		List<Order> orders = orderService.findByCustomer(user);
		model.addAttribute("orders", orders);
		return "order/list";
	}
	
	@RequestMapping("/order/detail/{id}")
	public String detail(Model model, @PathVariable("id") Long id) {
		Order order = orderService.findById(id);
		model.addAttribute("order", order);
		return "order/detail";
	}
	
	@RequestMapping("/order/cancel/{id}")
	public String cancel(@PathVariable("id") Long id) {
		Order order = orderService.findById(id);
		order.setOrderState(stateService.findById(-1)); //set orderstate thang -1
		orderService.update(order);//cap nhat trang thai don hang( stateid) tai service 
		return "forward:/order/list";
	}
	
	@RequestMapping("/order/items")
	public String findPurchasedItems(Model model) {
		User user = authService.getUser();
		Page<Product> page = productService.findByUser(user, Pageable.unpaged());
		model.addAttribute("itemsOfPage", page);
		return "product/list";
	}
	
	
}
