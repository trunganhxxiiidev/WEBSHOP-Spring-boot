package ta.webshop.jpa.entity;

import java.util.Date;

import java.util.List;
import jakarta.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "Orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@ManyToOne
	@JoinColumn(name = "customer")
	User customer;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "createdate")
	Date createDate;
	Double amount;
	@ManyToOne
	@JoinColumn(name = "master")
	User master;
	@ManyToOne
	@JoinColumn(name = "orderstateid")
	OrderState orderState;
	String address;
	
	@OneToMany(mappedBy = "order")
	List<OrderDetail> orderDetails;
}
