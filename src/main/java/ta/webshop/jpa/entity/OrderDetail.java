package ta.webshop.jpa.entity;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "Orderdetails")
public class OrderDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@ManyToOne
	@JoinColumn(name = "orderid")
	Order order;
	@ManyToOne
	@JoinColumn(name = "productid")
	Product product;
	@Column(name = "unitprice")
	Double unitPrice;
	Integer quantity;
	Double discount;
		
}
