package net.rbv.dao;

import java.util.List;

import net.rbv.model.User;

public interface UserDao {

	void registerUser(User user);

	User validateUserName(String userName);

	User validateMailId(String mailId);

	List<User> getUsers(int activated);

	User login(User user);

	void update(User validuser);

	User getUserByUserName(String username);
	
	
}
