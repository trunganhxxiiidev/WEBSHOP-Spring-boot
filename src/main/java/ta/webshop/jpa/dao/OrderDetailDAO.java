package ta.webshop.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import ta.webshop.jpa.entity.OrderDetail;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long>{

}
