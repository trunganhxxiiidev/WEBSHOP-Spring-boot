package ta.webshop.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ta.webshop.jpa.dao.UserDAO;
import ta.webshop.jpa.entity.User;

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
}
