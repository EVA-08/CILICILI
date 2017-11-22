package cilicili.service;

import cilicili.domain.Course;
import cilicili.domain.Lesson;
import cilicili.repository.CourseRepository;
import cilicili.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

/**
 * 一节课相关业务
 */
@Service
@Transactional
public class LessonService {
    private CourseRepository courseRepository;
    private LessonRepository lessonRepository;
    private ResourceService resourceService;

    @Autowired
    private void setCourseRepository(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Autowired
    private void setLessonRepository(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Autowired
    private void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
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
     * 获得一节课
     *
     * @param lessonId 一节课ID
     * @return 返回一节课
     */
    public Lesson getLesson(Integer lessonId) {
        return lessonRepository.findOne(lessonId);
    }

    /**
     * 给课程增加一节课
     * @param courseId 课程ID
     * @param lesson  一节课的信息
     * @param video 视频
     */
    public void addLesson(Integer courseId, Lesson lesson, MultipartFile video) {
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

    /**
     * 通过课程ID和序号查找一节课
     * @param courseId 课程ID
     * @param sequence 序号
     * @return 一节课
     */
    public Lesson findByCourseIdAndSequence(Integer courseId, Integer sequence) {
        return lessonRepository.findByCourseIdAndSequence(courseId, sequence);
    }


}
