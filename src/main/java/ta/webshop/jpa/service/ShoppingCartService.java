package ta.webshop.jpa.service;

import java.util.Collection;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface ShoppingCartService {
	void add(Integer id);
	void remove(Integer id);
	void update(Integer id, int quantity);
	void clear();
	
	default int getCount() {
		Collection<CartItem> items = this.getItems();
		return items.stream()
				.mapToInt(item -> item.getQuantity()).sum();
	}
	default double getAmount() {
		Collection<CartItem> items = this.getItems();
		return items.stream()
				.mapToDouble(item -> item.getAmount()).sum();
	}
	Collection<CartItem> getItems();
	
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class CartItem{
		String img;
		Integer id;
		String name;
		double price;
		int quantity;
		double discount;
		public double getAmount() {
			return price * quantity * (1 - discount);
		}
	}
}
