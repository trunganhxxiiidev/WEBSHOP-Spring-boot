package ta.webshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

@Service
public class SessionServiceImpl implements SessionService{
	@Autowired
	HttpSession session;

	@Override
	public void set(String name, Object value) {
		session.setAttribute(name, value);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(String name) {
		return (T) session.getAttribute(name);
	}

	@Override
	public void delete(String name) {
		session.removeAttribute(name);
	}


}
