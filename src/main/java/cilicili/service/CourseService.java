package cilicili.service;

import cilicili.domain.Course;
import cilicili.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 客户相关业务
 */
@Service
@Transactional
public class CourseService {
    /**
     * 获得注册课程列表
     *
     * @param user 用户
     * @return 返回注册课程列表
     */
    public List<Course> getRegisteredCourseList(User user) {
        return null;
    }

    /**
     * 注册课程
     *
     * @param user     用户
     * @param courseId 课程ID
     */
    public void registerCourse(User user, Integer courseId) {
    }

    /**
     * 创建课程
     *
     * @param course 课程信息
     */
    public void createCourse(Course course) {

    }

    /**
     * 删除课程
     *
     * @param courseId 课程ID
     */
    public void deleteCourse(Integer courseId) {

    }

    /**
     * 对课程进行模糊查询
     *
     * @param queryString 查询字符串
     * @return 课程列表
     */
    public List<Course> fuzzyQueryCourseList(String queryString) {
        return null;
    }
}
