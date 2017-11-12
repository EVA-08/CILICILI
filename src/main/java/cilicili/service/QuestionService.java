package cilicili.service;

import cilicili.domain.Lesson;
import cilicili.domain.Question;
import cilicili.repository.LessonRepository;
import cilicili.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * 问题相关业务
 */
@Service
@Transactional
public class QuestionService {
    private LessonRepository lessonRepository;
    private QuestionRepository questionRepository;

    @Autowired
    private void setLessonRepository(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Autowired
    private void setQuestionRepository(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    /**
     * 增加一个问题
     * @param lessonId 一节课ID
     * @param question 问题信息
     */
    public void addQuestion(Integer lessonId, Question question) {
        Lesson lesson = lessonRepository.findOne(lessonId);
        lesson.getQuestionSet().add(question);
        question.setLesson(lesson);
        lessonRepository.save(lesson);
    }

    /**
     * 获得一节课的问题列表
     * @param lessonId 一节课ID
     * @return 问题列表
     */
    public Set<Question> getQuestionList(Integer lessonId) {
        Lesson lesson = lessonRepository.findOne(lessonId);
        return lesson.getQuestionSet();
    }
}
