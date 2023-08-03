package ta.webshop.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ta.webshop.jpa.dao.RoleDAO;
import ta.webshop.jpa.entity.Role;
@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	RoleDAO dao;

	@Override
	public List<Role> findAll() {
		return dao.findAll();
	}
	
	@Override
	public Role findById(String id) {
		return dao.getById(id);
	}
}
