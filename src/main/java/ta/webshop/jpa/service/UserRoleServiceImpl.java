package ta.webshop.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ta.webshop.jpa.dao.UserRoleDAO;

@Service
public class UserRoleServiceImpl implements UserRoleService{
	@Autowired
	UserRoleDAO dao;

}
