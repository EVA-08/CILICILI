package cilicili.service;

import cilicili.domain.Question;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 问题相关业务
 */
@Service
@Transactional
public class QuestionService {
    /**
     * 增加一个问题
     * @param lessonId 一节课ID
     * @param question 问题信息
     */
    public void addQuestion(Integer lessonId, Question question) {

    }

    /**
     * 获得一节课的问题列表
     * @param lessonId 一节课ID
     * @return 问题列表
     */
    public List<Question> getQuestionList(Integer lessonId) {
        return null;
    }
}
