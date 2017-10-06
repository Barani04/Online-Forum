package net.rbv.dao;

import java.util.List;

import net.rbv.model.Question;

public interface QuestionDao {

	void submitQuery(Question quest);

	List<Question> getAllQuestions();

	Question getQuestionById(int queid);

}
