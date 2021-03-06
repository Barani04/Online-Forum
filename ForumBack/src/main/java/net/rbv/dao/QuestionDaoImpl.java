package net.rbv.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Question> getAllQuestions(boolean status) {
		Session session = sessionFactory.getCurrentSession();
		Query query= session.createQuery("from Question where approvestatus = ?");
		query.setBoolean(0, status);
		List<Question> queries = query.list();
		return queries;
	}

	@Override
	public Question getQuestionById(int queid) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Question where questionid = ?");
		query.setInteger(0, queid);
		Question que = (Question) query.uniqueResult();
		return que;
	}

	@Override
	public void updateQuestion(Question que) {
		Session session = sessionFactory.getCurrentSession();
		session.update(que);
		
	}
	
	
}
