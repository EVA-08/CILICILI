package cilicili.service;

import cilicili.domain.Course;
import cilicili.domain.User;
import cilicili.repository.CourseRepository;
import cilicili.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * 客户相关业务
 */
@Service
@Transactional
public class CourseService {
    private UserRepository userRepository;
    private CourseRepository courseRepository;

    @Autowired
    private void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    private void setCourseRepository(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    /**
     * 得到用户注册课程集合
     * @param userId 用户Id
     * @return 用户注册的所有课程
     */
    public Set<Course> GetRegisteredCourseRet(Integer userId) {
        User user = userRepository.findOne(userId);
        return user.getRegisteredCourseSet();
    }

    /**
     * 注册课程
     *
     * @param userId     用户ID
     * @param courseId 课程ID
     */
    public void registerCourse(Integer userId, Integer courseId) {
        User user = userRepository.findOne(userId);
        Course course = courseRepository.findOne(courseId);
        user.addRegisteredCourse(course);
        userRepository.save(user);
        courseRepository.save(course);
    }
    /**
     * 创建课程
     *
     * @param course 课程信息
     */
    public void createCourse(Course course) {
        Course course1 = new Course();
        course1.setName(course.getName());
        course1.setIntroduction(course.getIntroduction());
        courseRepository.save(course1);
    }

    /**
     * 删除课程
     *
     * @param courseId 课程ID
     */
    public void deleteCourse(Integer userId, Integer courseId) {
        User user = userRepository.findOne(userId);
        Course course = courseRepository.findOne(courseId);
        user.removeRegisteredCourse(course);
        userRepository.save(user);
        courseRepository.save(course);
    }

    /**
     * 对课程进行模糊查询
     *
     * @param queryString 查询字符串
     * @return 课程列表
     */
    public Set<Course> fuzzyQueryCourseList(String queryString) {
        return null;

    }

    /**
     * 获得所有课程
     *
     * @return 所有课程
     */
    public Iterable<Course> getAllCourse() {
        return courseRepository.findAll();
    }
}
