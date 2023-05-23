package ta.webshop.jpa.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ta.webshop.jpa.entity.Product;

public interface ProductService {

	List<Product> findByCategory(Integer id);
	
	Page<Product> findAllByCategoryId(int id,Pageable pageable);

	Page<Product> findByName(String keyword, Pageable pageable);

}