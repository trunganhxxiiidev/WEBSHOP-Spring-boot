package ta.webshop.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ta.webshop.jpa.dao.OrderDAO;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderDAO dao;

}
