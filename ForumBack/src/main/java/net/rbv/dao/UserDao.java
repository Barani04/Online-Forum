package net.rbv.dao;

import net.rbv.model.User;

public interface UserDao {

	void registerUser(User user);

	User validateUserName(String userName);

	User validateMailId(String mailId);
	
	
}
