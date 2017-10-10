package net.rbv.dao;

import java.util.List;

import net.rbv.model.Question;

public interface QuestionDao {

	void submitQuery(Question quest);

	List<Question> getAllQuestions(boolean status);

	Question getQuestionById(int queid);

	void updateQuestion(Question que);

}
