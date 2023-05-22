package ta.webshop.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ta.webshop.jpa.dao.RoleDAO;
@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	RoleDAO dao;
}
