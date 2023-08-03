package ta.webshop.jpa.service;

import java.util.List;

import ta.webshop.jpa.entity.Role;
import ta.webshop.jpa.entity.User;
import ta.webshop.jpa.entity.UserRole;

public interface UserRoleService {

	UserRole findByUserAndRole(User user, Role role);

	void delete(UserRole userRole);

	void create(UserRole userRole);

	List<UserRole> findAll();

}
