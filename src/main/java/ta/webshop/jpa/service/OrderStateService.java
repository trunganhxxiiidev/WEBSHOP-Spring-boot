package ta.webshop.jpa.service;

import java.util.List;

import ta.webshop.jpa.entity.OrderState;

public interface OrderStateService {

	OrderState findById(int i);

	List<OrderState> findAll();
	

}
