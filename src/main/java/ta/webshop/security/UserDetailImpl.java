package ta.webshop.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ta.webshop.jpa.entity.User;
import ta.webshop.jpa.entity.UserRole;

@SuppressWarnings("serial")
@Data

@NoArgsConstructor
@AllArgsConstructor

public class UserDetailImpl implements UserDetails {
	 User user;
	
	//  public UserDetailImpl(User user) {
	// 		this.user=user;
	// 	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<UserRole> userRoles= user.getUserRoles();
		if(userRoles==null || userRoles.isEmpty()) {
			return List.of();
		}
		return userRoles.stream()
				.map(ur->"ROLE_"+ur.getRole().getId())
				.map(au-> new SimpleGrantedAuthority(au)).toList();
	}

	@Override
	public String getPassword() {
		return user.getPassword();
		
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {
		return user.getEnabled();
	}


	

}
