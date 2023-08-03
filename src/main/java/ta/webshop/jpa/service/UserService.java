package ta.webshop.jpa.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ta.webshop.jpa.entity.User;

public interface UserService {

	void create(User user);

	boolean existByUsername(String username);

	User findByUsername(String username);

	void update(User user);

	

	void deleteByUsername(String username);

	Page<User> findCustomers(Pageable pageable);
	
	Page<User> findMasters(Pageable pageable);

	void create(User entity, List<String> roleIds);

	void update(User entity, List<String> roleIds);

}
