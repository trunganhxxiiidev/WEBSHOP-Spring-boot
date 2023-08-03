package ta.webshop.jpa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ta.webshop.jpa.entity.User;

public interface UserDAO extends JpaRepository<User, String>{
	
	@Query("SELECT u FROM User u WHERE u.userRoles IS EMPTY")
	Page<User> findCustomers(Pageable pageable);
	
	@Query("SELECT u FROM User u WHERE u.userRoles IS NOT EMPTY")
	Page<User> findMasters(Pageable pageable);

}
