package ta.webshop.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import ta.webshop.jpa.entity.User;

public interface UserDAO extends JpaRepository<User, String>{

}
