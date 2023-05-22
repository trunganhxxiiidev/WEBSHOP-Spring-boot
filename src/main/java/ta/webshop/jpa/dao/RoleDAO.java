package ta.webshop.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import ta.webshop.jpa.entity.Role;

public interface RoleDAO extends JpaRepository<Role, String>{

}
