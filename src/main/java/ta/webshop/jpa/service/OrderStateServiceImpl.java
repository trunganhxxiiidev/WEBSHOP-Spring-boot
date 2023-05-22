package ta.webshop.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ta.webshop.jpa.dao.OrderStateDAO;

@Service
public class OrderStateServiceImpl implements OrderStateService {
	@Autowired
	OrderStateDAO dao;

}
