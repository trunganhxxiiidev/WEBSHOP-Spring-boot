package ta.webshop.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ta.webshop.jpa.entity.Role;
import ta.webshop.jpa.entity.User;
import ta.webshop.jpa.entity.UserRole;

public interface UserRoleDAO extends JpaRepository<UserRole, Integer>{

	List<UserRole> findByUser(User user);

	UserRole findByUserAndRole(User user, Role role);

}
