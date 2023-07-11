package ta.webshop.jpa.service;

import java.util.Collection;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import ta.webshop.jpa.entity.Product;

@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{
	
	@Autowired
	ProductService productService;
	
	//map store cart item 
	Map<Integer, CartItem> map=new HashMap<>();
	
	@Override
	public void add(Integer id) {
		CartItem item=map.get(id);
		if(item!=null) {
			item.setQuantity(item.getQuantity()+1);
		}else {
			Product product = productService.findById(id);
			
			item = new CartItem(product.getImage(),id, product.getName(), product.getUnitPrice(), 1, product.getDiscount());
			map.put(id, item);
			
		}
		
	}

	@Override
	public void remove(Integer id) {
		map.remove(id);
		
	}

	@Override
	public void update(Integer id, int quantity) {
		CartItem item=map.get(id);
		item.setQuantity(quantity);
		
	}

	@Override
	public void clear() {
		map.clear();
		
	}

	@Override
	public Collection<CartItem> getItems() {
		return map.values();
	}

}
