package uk.co.kayratech.m2m.platform.dao;

import uk.co.kayratech.m2m.platform.model.User;

public interface UserDao extends BaseDao<User, String>, UserCustomDao {
	
	public User findUserByUsername(String username);
}
