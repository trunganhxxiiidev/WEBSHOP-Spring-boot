package ta.webshop.jpa.service;

import java.util.List;

import ta.webshop.jpa.entity.Category;

public interface CategoryService {

	List<Category> findAll();
}
