package ta.webshop.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ta.webshop.jpa.dao.UserDAO;
import ta.webshop.jpa.entity.User;

@Service
public class userDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	UserDAO userDAO;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// User user=userDAO.findById(username).get();
		// if(user==null) {
		// 	throw new UsernameNotFoundException("user not found");
		// }
		// return new  UserDetailImpl(user);
		
		//Optional<User> optionalUser = userDAO.findById(username);
		Optional<User> Ouser=userDAO.findById(username);
    User user = Ouser.orElseThrow(() -> new UsernameNotFoundException("User not found"));
    return new UserDetailImpl(user);
	}

}
