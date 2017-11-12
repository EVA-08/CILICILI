package cilicili.service;

import cilicili.domain.Course;
import cilicili.domain.Lesson;
import cilicili.repository.CourseRepository;
import cilicili.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * 一节课相关业务
 */
@Service
@Transactional
public class LessonService {
    private CourseRepository courseRepository;
    private LessonRepository lessonRepository;

    @Autowired
    private void setCourseRepository(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Autowired
    private void setLessonRepository(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    /**
     * 获得课程的所有课列表
     *
     * @param courseId 课程ID
     * @return 所有课列表
     */
    public Set<Lesson> getLessonList(Integer courseId) {

        Course course = courseRepository.findOne(courseId);
        return course.getLessonSet();
    }

    /**
     * 给课程增加一节课
     *
     * @param courseId 课程ID
     * @param lesson  一节课的信息
     */
    public void addLesson(Integer courseId, Lesson lesson) {
        Course course = courseRepository.findOne(courseId);
        course.getLessonSet().add(lesson);
        lesson.setCourse(course);
        courseRepository.save(course);
    }

    /**
     * 删除一节课
     *
     * @param courseId 课程ID
     * @param lessonId 课时ID
     */
    public void deleteLesson(Integer courseId, Integer lessonId) {
        Course course = courseRepository.findOne(courseId);
        Lesson lesson = lessonRepository.findOne(lessonId);
        course.getLessonSet().remove(lesson);
        lesson.setCourse(null);
        courseRepository.save(course);
    }
}
