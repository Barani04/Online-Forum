package net.rbv.dao;

import net.rbv.model.VoteDetails;

public interface VoteDetailDao {

	void saveVote(VoteDetails vote);

	VoteDetails getVotedUser(int queid, String username);

}
