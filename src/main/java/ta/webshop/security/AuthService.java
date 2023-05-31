package ta.webshop.security;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import ta.webshop.jpa.entity.User;

@Service("auth")
public class AuthService {
	@Autowired
	HttpServletRequest request;
	
	public boolean isAuthenticated() {
		return request.getRemoteUser()!=null;
	}
	
	public boolean hasAnyRole(String ...roles) {
		return this.isAuthenticated()&&
				Stream.of(roles).anyMatch(role->request.isUserInRole(role));
	}
	
	public User getUser() {
		Authentication authentication =(Authentication) request.getUserPrincipal();
		UserDetailImpl userDetails= (UserDetailImpl) authentication.getCredentials();
		return	userDetails.getUser();
	}
	
	public void setUser(User user) {
		Authentication authentication =(Authentication) request.getUserPrincipal();
		UserDetailImpl userDetails= (UserDetailImpl) authentication.getCredentials();
		userDetails.setUser(user);
	}
	
}
