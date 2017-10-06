package net.rbv.dao;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.rbv.model.VoteQuestion;

@Repository
@Transactional
public class VoteQueDaoImpl implements VoteQueDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public VoteQuestion isVotedQuery(int queid, String username) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from VoteQuestion where questionid = ? and username = ?");
		query.setInteger(0, queid);
		query.setString(1, username);
		VoteQuestion vq = (VoteQuestion) query.uniqueResult();
		return vq;
	}

}
