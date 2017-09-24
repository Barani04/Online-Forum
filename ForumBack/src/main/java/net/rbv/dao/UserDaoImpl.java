package net.rbv.dao;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.rbv.model.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void registerUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.save(user);

	}

	@Override
	public User validateUserName(String userName) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User where userName =?");
		query.setString(0, userName);
		User user = (User) query.uniqueResult();
		return user;
	}

	@Override
	public User validateMailId(String mailId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User where mailId =?");
		query.setString(0, mailId);
		User user = (User) query.uniqueResult();
		return user;
	}

}
