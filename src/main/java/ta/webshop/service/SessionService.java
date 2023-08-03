package ta.webshop.service;

public interface SessionService {




	void set(String name, Object value);

	<T> T get(String name);

	void delete(String name);

}
