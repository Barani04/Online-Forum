package net.rbv.dao;

import java.util.List;

import net.rbv.model.Answer;

public interface AnswerDao {

	void submitAnswer(Answer ans);

	List<Answer> getAllAnswers(int queid);

	Answer getAnswerById(int answerId);

	void updateAnswer(Answer ans);

}
