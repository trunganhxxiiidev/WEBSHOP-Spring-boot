package ta.webshop.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import ta.webshop.jpa.entity.OrderState;

public interface OrderStateDAO extends JpaRepository<OrderState, Integer>{

}
