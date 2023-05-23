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
@Table(name = "Roles")
public class Role {
	@Id
	String id;
	String name;
	
	@OneToMany(mappedBy = "role",fetch = FetchType.LAZY)
	List<UserRole> userRoles;
}
