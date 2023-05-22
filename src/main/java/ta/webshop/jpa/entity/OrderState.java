package ta.webshop.jpa.entity;

import java.util.List;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "Orderstates")
public class OrderState {
	@Id
	Integer id;
	String name;
	
	@OneToMany(mappedBy = "orderState")
	List<Order> orders;
}
