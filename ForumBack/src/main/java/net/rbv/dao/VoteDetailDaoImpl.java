package net.rbv.dao;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.rbv.model.VoteDetails;

@Repository
@Transactional
public class VoteDetailDaoImpl implements VoteDetailDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void saveVote(VoteDetails vote) {
		Session session = sessionFactory.getCurrentSession();
		session.save(vote);
		
	}

	@Override
	public VoteDetails getVotedUser(int queid, String username) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from VoteDetails where questionid = ? and username = ?");
		query.setInteger(0, queid);
		query.setString(1, username);
		VoteDetails vote = (VoteDetails) query.uniqueResult();
		return vote;
	}

}
