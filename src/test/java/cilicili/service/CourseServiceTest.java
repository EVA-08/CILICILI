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
        course1.setIntroduction("It is an useful resources!");

        User user1 = new User();
        user1.setUsername("aaa");
        user1.setPassword("123");
        user1.setIdentity(User.Identity.STUDENT);
        userRepository.save(user1);

        courseRepository.save(course1);
        course1 = courseRepository.findByName(course1.getName());
        courseService.registerCourse(user1.getId(), course1.getId());
        Set<Course> RegisteredCourse = courseService.GetRegisteredCourseSet(user1.getId());
        Set<Course> RegisteredCourse1 = new HashSet<>();
        RegisteredCourse1.add(course1);
        Assert.assertEquals(RegisteredCourse1, RegisteredCourse);
    }

    /*
    @Test
    public void createCourse() {
        Course course1 = new Course();
        course1.setName("software engineering");
        course1.setIntroduction("It is an useful resources!");
        courseService.createCourse(course1);
        Course result = courseRepository.findByName(course1.getName());
        Assert.assertEquals(result, course1);
    }
*/

    @Test
    public void unRegisterCourseTest() {
        Course course1 = new Course();
        course1.setName("software engineering");
        course1.setIntroduction("It is an useful resources!");
        courseRepository.save(course1);

        Course course2 = new Course();
        course2.setName("math");
        course2.setIntroduction("It is an interesting resources!");
        courseRepository.save(course2);

        User user1 = new User();
        user1.setUsername("aaa");
        user1.setPassword("123");
        user1.setIdentity(User.Identity.STUDENT);
        userRepository.save(user1);

        user1.addRegisteredCourse(course1);
        user1.addRegisteredCourse(course2);
        Set<Course> result = user1.getRegisteredCourseSet();
        Set<Course> registeredcourse = new HashSet<>();
        registeredcourse.add(course1);
        registeredcourse.add(course2);
        Assert.assertEquals(registeredcourse, result);

        courseService.unregisterCourse(user1.getId(), course1.getId());
        result = user1.getRegisteredCourseSet();
        registeredcourse.remove(course1);
        Assert.assertEquals(registeredcourse, result);
    }

    @Test
    public void fuzzyQueryCourseListTest() {
        Course course1 = new Course();
        course1.setName("Computer Network");
        course1.setIntroduction("ComputerNetwork...");
        courseRepository.save(course1);

        Course course2 = new Course();
        course2.setName("Computer Graphics");
        course2.setIntroduction("ComputerGraphics...");
        courseRepository.save(course2);

        Course course3 = new Course();
        course3.setName(" Computer Organization");
        course3.setIntroduction("ComputerOrganization...");
        courseRepository.save(course3);

        Set<Course> result = courseService.fuzzyQueryCourseList("Computer");
        Set<Course> ExpectedCourseSet = new HashSet<>();
        ExpectedCourseSet.add(course1);
        ExpectedCourseSet.add(course2);
        ExpectedCourseSet.add(course3);
        Assert.assertEquals(ExpectedCourseSet, result);
    }

    @Test
    public void deleteCourseTest() {
        User user1 = new User();
        user1.setIdentity(User.Identity.STUDENT);
        user1.setUsername("a");
        user1.setPassword("aaa");
        userRepository.save(user1);

        Course course1 = new Course();
        course1.setName("A");
        course1.setIntroduction("AAA");
        courseRepository.save(course1);

        Course course2 = new Course();
        course2.setName("B");
        course2.setIntroduction("BBB");
        courseRepository.save(course2);

        Course course3 = new Course();
        course3.setName("C");
        course3.setIntroduction("CCC");
        courseRepository.save(course3);

        courseService.registerCourse(user1.getId(), course1.getId());
        courseService.registerCourse(user1.getId(), course2.getId());
        courseService.registerCourse(user1.getId(), course3.getId());

        Set<Course> result = courseService.GetRegisteredCourseSet(user1.getId());
        Set<Course> expectedCourseSet = new HashSet<>();
        expectedCourseSet.add(course1);
        expectedCourseSet.add(course2);
        expectedCourseSet.add(course3);
        Assert.assertEquals(expectedCourseSet, result);
        Assert.assertNotNull(course1.getId());

        courseService.deleteCourse(course1.getId());
        result = courseService.GetRegisteredCourseSet(user1.getId());
        expectedCourseSet.remove(course1);
        Assert.assertEquals(expectedCourseSet, result);
        Assert.assertEquals(null, courseRepository.findByName("A"));
    }


}


