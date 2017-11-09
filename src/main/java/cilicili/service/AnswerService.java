package cilicili.service;

import cilicili.domain.Answer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 回答相关业务
 */
@Service
@Transactional
public class AnswerService {
    /**
     * 增加一条回答
     *
     * @param questionId 问题ID
     * @param answer     回答信息
     */
    public void addAnswer(Integer questionId, Answer answer) {

    }

    /**
     * 返回回答列表
     *
     * @param questionId 问题ID
     * @return 回答列表
     */
    public List<Answer> getAnswerList(Integer questionId) {
        return null;
    }
}
