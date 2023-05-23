package ta.webshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ta.webshop.interceptor.AppInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{
	@Autowired
	AppInterceptor appInterceptor;
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	
		registry.addInterceptor(appInterceptor).addPathPatterns("/**")
		.excludePathPatterns("/admin/**","/js/**","/css/**","/images/**","/api/**");
	}

}
