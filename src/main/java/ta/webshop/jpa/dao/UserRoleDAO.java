package ta.webshop.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import ta.webshop.jpa.entity.UserRole;

public interface UserRoleDAO extends JpaRepository<UserRole, Integer>{

}
