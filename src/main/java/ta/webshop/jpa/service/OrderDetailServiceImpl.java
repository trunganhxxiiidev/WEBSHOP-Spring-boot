package ta.webshop.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ta.webshop.jpa.dao.OrderDetailDAO;

@Service
public class OrderDetailServiceImpl  implements OrderDetailService{
	@Autowired
	OrderDetailDAO dao;

}
