package ta.webshop.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ta.webshop.jpa.dao.UserRoleDAO;
import ta.webshop.jpa.entity.Role;
import ta.webshop.jpa.entity.User;
import ta.webshop.jpa.entity.UserRole;

@Service
public class UserRoleServiceImpl implements UserRoleService{
	@Autowired
	UserRoleDAO dao;

	@Override
	public UserRole findByUserAndRole(User user, Role role) {
		return dao.findByUserAndRole(user, role);
	}

	@Override
	public void delete(UserRole userRole) {
		dao.delete(userRole);	
	}

	@Override
	public void create(UserRole userRole) {
		dao.save(userRole);
		
	}

	@Override
	public List<UserRole> findAll() {
		return dao.findAll();
	}

}
