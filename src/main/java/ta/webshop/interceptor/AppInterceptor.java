package ta.webshop.interceptor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ta.webshop.jpa.service.CategoryService;

@Service
public class AppInterceptor implements HandlerInterceptor {
	@Autowired
	CategoryService categoryService;
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
		if(modelAndView!=null) {
			modelAndView.addObject("categories",categoryService.findAll());
		};
	}
}
