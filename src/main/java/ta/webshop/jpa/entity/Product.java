package ta.webshop.jpa.entity;

import java.util.Date;

import java.util.List;
import jakarta.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "Products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String name;
	String image;
	@Column(name = "unitprice")
	Double unitPrice;
	Integer quantity;
	@ManyToOne
	@JoinColumn(name = "categoryid")
	Category category;
	Double discount;
	@Column(name = "likecount")
	Integer likeCount;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "createdate")
	Date createDate;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<OrderDetail> orderDetails;
}
