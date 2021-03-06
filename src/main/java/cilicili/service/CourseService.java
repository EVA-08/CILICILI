package cilicili.service;

import cilicili.domain.Course;
import cilicili.domain.Resource;
import cilicili.domain.User;
import cilicili.repository.CourseRepository;
import cilicili.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.Iterator;
import java.util.Set;

/**
 * 客户相关业务
 */
@Service
@Transactional
public class CourseService {
    private UserRepository userRepository;
    private CourseRepository courseRepository;
    private ResourceService resourceService;

    @Autowired
    private void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    private void setCourseRepository(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Autowired
    private void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    /**
     * 得到用户注册课程集合
     * @param userId 用户Id
     * @return 用户注册的所有课程
     */
    public Set<Course> GetRegisteredCourseSet(Integer userId) {
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
    }
    /**
     * 创建课程
     * @param course 课程信息
     * @param author 作者
     * @param image 上传的图像
     */
    public void createCourse(Course course, User author, MultipartFile image) {
        String filename = image.getOriginalFilename();
        String suffix = filename.substring(filename.lastIndexOf('.'));
        Course course1 = new Course();
        courseRepository.save(course1);
        resourceService.store(image, "image/" + course.getId() + Instant.now().toEpochMilli() + suffix);
        Resource image1 = new Resource();
        image1.setPath("image/" + course1.getId() + suffix);
        course1.setName(course.getName());
        course1.setIntroduction(course.getIntroduction());
        course1.setAuthor(author);
        course1.setImage(image1);
        author.createCourse(course1);
        userRepository.save(author);
    }

    /**
     * 退选课程
     *@param  userId  用户ID
     * @param courseId 课程ID
     */
    public void unregisterCourse(Integer userId, Integer courseId) {
        User user = userRepository.findOne(userId);
        Course course = courseRepository.findOne(courseId);
        user.removeRegisteredCourse(course);
        userRepository.save(user);
    }

    /**
     * 删除课程
     * @param courseId 课程ID
     */
    public void deleteCourse(Integer courseId) {
        Course course = courseRepository.findOne(courseId);
        Iterator<User> userIterator = course.getRegisteredUserSet().iterator();
        while (userIterator.hasNext()) {
            User user = userIterator.next();
            user.getRegisteredCourseSet().remove(course);
            userIterator.remove();
            userRepository.save(user);
        }
        resourceService.delete(courseRepository.findOne(courseId).getImage().getPath());
        User author = course.getAuthor();
        author.removeCourse(author.getCreatedCourseSet().stream().filter(t -> t.getId().equals(courseId)).findFirst().get());
        userRepository.save(author);
        courseRepository.delete(courseId);
    }
    /**
     * 对课程进行模糊查询
     *
     * @param queryString 查询字符串
     * @return 课程列表
     */
    public Set<Course> fuzzyQueryCourseList(String queryString) {
        return courseRepository.findByNameContaining(queryString);
    }

    /**
     * 获得所有课程
     *
     * @return 所有课程
     */
    public Iterable<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    /**
     * 根据课程ID得到课程
     *
     * @param courseId 课程ID
     * @return 课程
     */
    public Course getCourse(Integer courseId) {
        return courseRepository.findOne(courseId);
    }


    /**
     * 更新课程信息
     *
     * @param course 课程信息
     * @param image  上传图片
     */
    public void updateCourse(Course course, User author, MultipartFile image) {
        Course course1 = author.getCreatedCourseSet().stream().filter(t -> t.getId().equals(course.getId())).findFirst().get();
        if (!image.isEmpty()) {
            String filename = image.getOriginalFilename();
            String suffix = filename.substring(filename.lastIndexOf('.'));
            String imagePath = "image/" + course.getId() + Instant.now().toEpochMilli() + suffix;
            resourceService.store(image, imagePath);
            Resource image1 = new Resource();
            image1.setPath(imagePath);
            course1.setImage(image1);
        }
        course1.setName(course.getName());
        course1.setIntroduction(course.getIntroduction());
        userRepository.save(author);
    }
}
