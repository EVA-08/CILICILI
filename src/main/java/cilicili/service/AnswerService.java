package cilicili.service;

import cilicili.domain.Answer;
import cilicili.domain.Question;
import cilicili.repository.AnswerRepository;
import cilicili.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * 回答相关业务
 */
@Service
@Transactional
public class AnswerService {
    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;

    @Autowired
    private void setQuestionRepository(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Autowired
    private void setAnswerRepository(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }
    /**
     * 增加一条回答
     *
     * @param questionId 问题ID
     * @param answer     回答信息
     */
    public void addAnswer(Integer questionId, Answer answer) {
        Question question = questionRepository.findOne(questionId);
        question.getAnswerSet().add(answer);
        answer.setQuestion(question);
        questionRepository.save(question);
        answerRepository.save(answer);
    }

    /**
     * 返回回答列表
     *
     * @param questionId 问题ID
     * @return 回答列表
     */
    public Set<Answer> getAnswerList(Integer questionId) {
        Question question = questionRepository.findOne(questionId);
        return question.getAnswerSet();
    }
}
