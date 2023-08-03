package ta.webshop.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ta.webshop.jpa.dao.RoleDAO;
import ta.webshop.jpa.dao.UserDAO;
import ta.webshop.jpa.dao.UserRoleDAO;
import ta.webshop.jpa.entity.Role;
import ta.webshop.jpa.entity.User;
import ta.webshop.jpa.entity.UserRole;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDAO dao;

	@Override
	public void create(User user) {
		dao.save(user);
		
	}

	@Override
	public boolean existByUsername(String username) {	
		return dao.existsById(username);
	}

	@Override
	public User findByUsername(String username) {
		return dao.getById(username);
	}

	@Override
	public void update(User user) {
		dao.save(user);		
	}

	@Override
	public void deleteByUsername(String username) {
		dao.deleteById(username);
		
	}

	@Override
	public Page<User> findCustomers(Pageable pageable) {
		return dao.findCustomers(pageable);
	}
	@Override
	public Page<User> findMasters(Pageable pageable) {
		return dao.findMasters(pageable);
	}

	@Autowired
	RoleDAO rdao;
	
	@Autowired
	UserRoleDAO urdao;
	
	@Transactional
	@Override
	public void create(User user, List<String> roleIds) {
		dao.save(user);
		List<UserRole> userRoles = roleIds.stream().map(id -> {
			Role role = rdao.getById(id);
			return new UserRole(null, role, user);
		}).toList();
		urdao.saveAll(userRoles);
	}

	@Transactional
	@Override
	public void update(User user, List<String> roleIds) {
		dao.save(user);
		List<UserRole> userRoles = urdao.findByUser(user);
		urdao.deleteAll(userRoles);
		userRoles = roleIds.stream().map(id -> {
			Role role = rdao.getById(id);
			return new UserRole(null, role, user);
		}).toList();
		user.setUserRoles(userRoles);
		urdao.saveAll(userRoles);
	}
}
