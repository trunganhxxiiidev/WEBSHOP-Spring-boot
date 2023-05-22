package ta.webshop.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ta.webshop.jpa.dao.ProductDAO;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDAO dao;

}
