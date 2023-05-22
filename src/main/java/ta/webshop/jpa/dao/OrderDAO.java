package ta.webshop.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import ta.webshop.jpa.entity.Order;

public interface OrderDAO extends JpaRepository<Order, Long>{

}
