package ta.webshop;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	@Autowired
	UserDetailsService userDetailsService;


	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors().disable();
		http.csrf().disable();
		 http
	        .httpBasic()
	            .and()
	        .authorizeRequests()
	                .requestMatchers(
	                		"/order/**")
	                .authenticated()
	                .anyRequest().permitAll();
		 
		 http.formLogin().loginPage("/security/login/form")
		 				.loginProcessingUrl("/spring/login")
		 				.defaultSuccessUrl("/security/login/success")
		 				.failureUrl("/security/login/failure");
		 
		 http.rememberMe().tokenValiditySeconds(30*24*24*60*60);
		 http.logout()
		 	.logoutUrl("/spring/logout")
		 	.logoutSuccessUrl("/security/logout/success")
		 	.addLogoutHandler(new SecurityContextLogoutHandler());
		 
		 http.exceptionHandling()
		 .accessDeniedPage("/security/access/denied");

		 	        return http.build();
	}
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() throws Exception {
		return (web)-> web.ignoring().requestMatchers("/images/**","/js/**","/css");
	}
	
		@Bean // Hỗ trợ cơ chế mã hóa và so sánh mật khẩu
		public BCryptPasswordEncoder getPasswordEncoder() {
			return new BCryptPasswordEncoder();
		}
		
		@Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        auth
	                .userDetailsService(userDetailsService)
	                .passwordEncoder( getPasswordEncoder() );
	    }
	
	
}








