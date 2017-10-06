package net.rbv.dao;

import net.rbv.model.VoteQuestion;

public interface VoteQueDao {

	VoteQuestion isVotedQuery(int queid, String username);
	
}
