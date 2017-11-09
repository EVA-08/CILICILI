package cilicili.service;

import cilicili.domain.Lesson;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 一节课相关业务
 */
@Service
@Transactional
public class LessonService {
    /**
     * 获得课程的所有课列表
     *
     * @param courseId 课程ID
     * @return 所有课列表
     */
    public List<Lesson> getlessonList(Integer courseId) {
        return null;
    }

    /**
     * 给课程增加一节课
     *
     * @param courseId 课程ID
     * @param lesson   一节课信息
     */
    public void addLesson(Integer courseId, Lesson lesson) {

    }

    /**
     * 删除一节课
     *
     * @param courseId 课程ID
     * @param lessonId 课程ID
     */
    public void deleteLesson(Integer courseId, Integer lessonId) {

    }
}
