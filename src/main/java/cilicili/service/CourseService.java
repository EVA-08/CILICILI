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
     * @param user 用户个人信息
     * @return 用户注册的课程集合
     */
    public Set<Course> GetRegisteredCourseRet(User user) {
        return user.getRegisteredCourseSet();
    }

    /**
     * 注册课程
     *
     * @param user     用户
     * @param courseId 课程ID
     */
    public void registerCourse(User user, Integer courseId) {
        User user1 = userRepository.findOne(user.getId());
        Course course1 = courseRepository.findOne(courseId);
        user1.addRegisteredCourse(course1);
        userRepository.save(user1);
        courseRepository.save(course1);
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
    public void deleteCourse(User user, Integer courseId) {
        User user1 = userRepository.findOne(user.getId());
        Course course1 = courseRepository.findOne(courseId);
        user1.removeRegisteredCourse(course1);
        userRepository.save(user1);
        courseRepository.save(course1);
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
