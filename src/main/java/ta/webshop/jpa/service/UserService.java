package ta.webshop.jpa.service;

import ta.webshop.jpa.entity.User;

public interface UserService {

	void create(User user);

	boolean existByUsername(String username);

	User findByUsername(String username);

	void update(User user);

}
