package ta.webshop.jpa.service;



import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import ta.webshop.jpa.dao.ProductDAO;
import ta.webshop.jpa.entity.Product;

@Service

public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDAO dao;

	@Override
	public List<Product> findByCategory(Integer id) {
		
		return dao.findByCategoryId(id);
	}

	@Override
	public Page<Product> findAllByCategoryId(int id, Pageable pageable) {
		
		return dao.findAllByCategoryId(id, pageable);
	}

	@Override
	public Page<Product> findByName(String keyword, Pageable pageable) {
		
		return dao.findByName(keyword,pageable);
	}

	@Override
	public Page<Product> findByBest(Pageable pageable) {
		List<Integer> ids=dao.findByBestIds(pageable);
		return dao.findAllByIds(ids,pageable);
	}

	@Override
	public Page<Product> findByDiscount(Pageable pageable) {
		
		return dao.findByDiscount(pageable);
	}

	@Override
	public Page<Product> findByLikes(Pageable pageable) {
		
		return dao.findByLikes(pageable);
	}

	@Override
	public Page<Product> findByLatest(Pageable pageable) {
		
		return dao.findByLatest(pageable);
	}

	
	
	@Override
	public Product findById(Integer id) {
		
		return dao.getById(id);
	}

	@Override
	public void update(Product product) {
		dao.save(product);	
	}
	

	

}
