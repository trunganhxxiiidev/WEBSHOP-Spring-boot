package ta.webshop.jpa.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ta.webshop.jpa.dao.CategoryDAO;
import ta.webshop.jpa.entity.Category;

@Service

public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryDAO dao;

	@Override
	public List<Category> findAll() {
	
		return dao.findAll();
	}

	@Override
	public Category findById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
		
	}

	@Override
	public void create(Category entity) {
		dao.save(entity);
		
	}

	@Override
	public void update(Category entity) {
		dao.save(entity);
		
	}
	
	
}