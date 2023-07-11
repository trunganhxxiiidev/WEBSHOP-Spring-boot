package ta.webshop.jpa.service;

import java.util.Collection;

import java.util.List;

import ta.webshop.jpa.entity.Order;
import ta.webshop.jpa.entity.OrderState;
import ta.webshop.jpa.entity.User;
import ta.webshop.jpa.service.ShoppingCartService.CartItem;

public interface OrderService {

	void purchase(Order order, Collection<CartItem> items);
	
	List<Order> findByCustomer(User user);

	Order findById(Long id);

	List<Order> findAll();

	void deleteById(Long id);

	void update(Order entity);

	List<Order> findByOrderState(OrderState orderState);

	void save(Order order);

	

	
}
