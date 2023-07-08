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
@Table(name = "Users")
public class User {
	@Id
	String username;
	String password;
	String fullname;
	String photo;
	String email;
	Boolean enabled;
	
	@OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
	List<Order> customerOrders;
	
	@OneToMany(mappedBy = "master", fetch = FetchType.EAGER)
	List<Order> masterOrders;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	List<UserRole> userRoles;

	
}
