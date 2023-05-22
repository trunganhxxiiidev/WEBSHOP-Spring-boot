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
}
