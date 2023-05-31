package ta.webshop.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import ta.webshop.jpa.entity.Category;

public interface CategoryDAO extends JpaRepository<Category, Integer>{

}
 