package ta.webshop.jpa.service;

import java.util.Collection;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ta.webshop.jpa.dao.OrderDAO;
import ta.webshop.jpa.dao.OrderDetailDAO;
import ta.webshop.jpa.entity.Order;
import ta.webshop.jpa.entity.OrderDetail;
import ta.webshop.jpa.entity.OrderState;
import ta.webshop.jpa.entity.User;
import ta.webshop.jpa.service.ShoppingCartService.CartItem;
import ta.webshop.jpa.dao.ProductDAO;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderDAO dao;
	
	@Autowired
	ProductDAO pdao;
	
	@Autowired
	OrderDetailDAO ddao;
	
	@Transactional
	@Override
	public void purchase(Order order, Collection<CartItem> items) {
		// TODO Auto-generated method stub
		dao.save(order);
		List<OrderDetail> details = items.stream().map(item -> {
			OrderDetail detail = new OrderDetail();
			detail.setDiscount(item.getDiscount());
			detail.setOrder(order);
			detail.setProduct(pdao.getById(item.getId()));
			detail.setQuantity(item.getQuantity());
			detail.setUnitPrice(item.getPrice());
			return detail;
		}).toList();
		ddao.saveAll(details);
	}

	@Override
	public List<Order> findByCustomer(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Order entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Order> findByOrderState(OrderState orderState) {
		// TODO Auto-generated method stub
		return null;
	}

}
