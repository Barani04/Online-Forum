package net.rbv.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.rbv.model.Answer;

@Repository
@Transactional
public class AnswerDaoImpl implements AnswerDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void submitAnswer(Answer ans) {
		Session session = sessionFactory.getCurrentSession();
		session.save(ans);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Answer> getAllAnswers(int queid) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Answer where question.questionId = ?");
		query.setInteger(0, queid);
		List<Answer> answers = query.list();
		return answers;
	}

	@Override
	public Answer getAnswerById(int answerId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Answer where answerId = ?");
		query.setInteger(0, answerId);
		Answer ans = (Answer) query.uniqueResult();
		return ans;
	}

	@Override
	public void updateAnswer(Answer ans) {
		Session session = sessionFactory.getCurrentSession();
		session.update(ans);
		
	}

}
