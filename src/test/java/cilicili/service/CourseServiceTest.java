package cilicili.service;

import cilicili.Application;
import cilicili.domain.Course;
import cilicili.domain.User;
import cilicili.repository.CourseRepository;
import cilicili.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(classes = Application.class)
public class CourseServiceTest {
    private CourseService courseService;
    private CourseRepository courseRepository;
    private UserService userService;
    private UserRepository userRepository;

    @Autowired
    private void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @Autowired
    private void setCourseRepository(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Autowired
    private void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    public void registeredCourseTest() {
        Course course1 = new Course();
        course1.setName("software engineering");
        course1.setIntroduction("It is an useful course!");

        User user1 = new User();
        user1.setUsername("aaa");
        user1.setPassword("123");
        user1.setIdentity(User.Identity.STUDENT);
        userRepository.save(user1);
        user1 = userRepository.findByUsername(user1.getUsername());

        courseRepository.save(course1);
        course1 = courseRepository.findByName(course1.getName());
        courseService.registerCourse(user1.getId(), course1.getId());
        Set<Course> RegisteredCourse = courseService.GetRegisteredCourseSet(user1.getId());
        Set<Course> RegisteredCourse1 = new HashSet<>();
        RegisteredCourse1.add(course1);
        Assert.assertEquals(RegisteredCourse1, RegisteredCourse);
    }

    @Test
    public void creatCourse() {
        Course course1 = new Course();
        course1.setName("software engineering");
        course1.setIntroduction("It is an useful course!");
        courseService.createCourse(course1);
        Course result = courseRepository.findByName(course1.getName());
        Assert.assertEquals(result, course1);
    }

    @Test
    public void deleteCourseTest() {
        Course course1 = new Course();
        course1.setName("software engineering");
        course1.setIntroduction("It is an useful course!");
        courseRepository.save(course1);
        course1 = courseRepository.findByName(course1.getName());

        Course course2 = new Course();
        course2.setName("math");
        course2.setIntroduction("It is an interesting course!");
        courseRepository.save(course2);
        course2 = courseRepository.findByName(course2.getName());

        User user1 = new User();
        user1.setUsername("aaa");
        user1.setPassword("123");
        user1.setIdentity(User.Identity.STUDENT);
        userRepository.save(user1);
        user1 = userRepository.findByUsername(user1.getUsername());

        user1.addRegisteredCourse(course1);
        user1.addRegisteredCourse(course2);
        Set<Course> result = user1.getRegisteredCourseSet();
        Set<Course> registeredcourse = new HashSet<>();
        registeredcourse.add(course1);
        registeredcourse.add(course2);
        Assert.assertEquals(registeredcourse, result);

        courseService.deleteCourse(user1.getId(), course1.getId());
        result = user1.getRegisteredCourseSet();
        registeredcourse.remove(course1);
        Assert.assertEquals(registeredcourse, result);
    }


}

