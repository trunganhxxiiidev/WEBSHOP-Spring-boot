package ta.webshop.jpa.service;

import java.util.List;

import ta.webshop.jpa.entity.Role;

public interface RoleService {

	List<Role> findAll();
	public Role findById(String id);
}
