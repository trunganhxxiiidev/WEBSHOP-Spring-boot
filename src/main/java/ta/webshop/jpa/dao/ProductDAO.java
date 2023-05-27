package ta.webshop.jpa.dao;

import java.util.List;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ta.webshop.jpa.entity.Product;
import ta.webshop.jpa.entity.Report;
import ta.webshop.jpa.entity.Report2;

public interface ProductDAO extends JpaRepository<Product, Integer>{
	@Query("SELECT p FROM Product p WHERE p.unitPrice BETWEEN ?1 AND ?2")
	List<Product> findByUnitPrice(double min, double max);
	
	@Query("SELECT p FROM Product p WHERE p.category.id=:cid")
	List<Product> findByCategoryId(@Param("cid") Integer id);
	
	@Query("SELECT p.name AS name, "
			+ "p.unitPrice AS price, "
			+ "p.createDate AS create "
			+ "FROM Product p "
			+ "WHERE year(p.createDate) IN (2020)")
	List<Report> findBy2021Items();
	
	@Query("SELECT p.category.nameVn AS category, "
			+ "sum(p.quantity) AS totalQuantity, "
			+ "sum(p.quantity * p.unitPrice) AS totalValue "
			+ "FROM Product p "
			+ "GROUP BY p.category.nameVn")
	List<Report2> inventory();
	
	@Query("SELECT p FROM Product p WHERE p.unitPrice > ?1")
	Page<Product> findByMinPrice(double min, Pageable pageable);

	List<Product> findByUnitPriceGreaterThan(Double minPrice);

	Page<Product> findAllByUnitPriceGreaterThan(Double minPrice, Pageable pageable);

	@Query("SELECT p FROM Product p WHERE p.category.id=?1")
	Page<Product> findAllByCategoryId(int id, Pageable pageable);

	@Query("SELECT p FROM Product p WHERE CONCAT(p.name, ' ', p.category.name, ' ',p.category.nameVn) LIKE %?1%")
	Page<Product> findByName(String keyword, Pageable pageable);
	
	@Query("SELECT p FROM Product p  ORDER BY  p.createDate DESC")
	Page<Product> findByBest(Pageable pageable);

	@Query("SELECT p FROM Product p WHERE p.discount>0 ORDER BY p.discount DESC")
	Page<Product> findByDiscount(Pageable pageable);

	@Query("SELECT p FROM Product p WHERE p.likeCount>0 ORDER BY p.likeCount DESC")
	Page<Product> findByLikes(Pageable pageable);

	@Query("SELECT p FROM Product p  ORDER BY  p.createDate DESC")
	Page<Product> findByLatest(Pageable pageable);
	
	@Query("SELECT d.product.id From OrderDetail d"
			+ " GROUP BY d.product.id"
			+ " ORDER BY sum(d.unitPrice*d.quantity*(1-d.discount))")
	List<Integer> findByBestIds(Pageable pageable);

	@Query("SELECT p from Product p WHERE p.id IN ?1")
	Page<Product> findAllByIds(List<Integer> ids, Pageable pageable);
}
