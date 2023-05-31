package ta.webshop.security;

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
		try {
			User user= userDAO.findById(username).get();
			return  new  UserDetailImpl(user);
		} catch (UsernameNotFoundException e) {
			throw new UsernameNotFoundException(username+" not found");
			// TODO: handle exception
		}
	}
	

}
