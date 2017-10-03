package net.rbv.dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.rbv.model.Question;

@Repository
@Transactional
public class QuestionDaoImpl implements QuestionDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void submitQuery(Question quest) {
		Session session = sessionFactory.getCurrentSession();
		session.save(quest);
	}
	
	
}
