package cilicili.service;

import cilicili.Application;
import cilicili.domain.Course;
import cilicili.domain.Lesson;
import cilicili.repository.CourseRepository;
import cilicili.repository.LessonRepository;
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
public class LessonServiceTest {
    private CourseRepository courseRepository;
    private LessonRepository lessonRepository;
    private LessonService lessonService;

    @Autowired
    private void setCourseRepository(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Autowired
    private void setLessonRepository(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Autowired
    private void setLessonService(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @Test
    public void addLessonTest() {
        Course course = new Course();
        course.setName("Software Engineering");
        course.setIntroduction("It is an useful resource!");
        courseRepository.save(course);
        course = courseRepository.findByName(course.getName());

        Lesson lesson1 = new Lesson();
        lesson1.setName("Requirement Analysis");
        lesson1.setDescription("It is the begining of a project");
        lesson1.setSequence(1);
        lessonRepository.save(lesson1);
        lesson1 = lessonRepository.findByName(lesson1.getName());

        Lesson lesson2 = new Lesson();
        lesson2.setName("System Design");
        lesson2.setDescription("Design a project generally");
        lesson2.setSequence(2);
        lessonRepository.save(lesson2);
        lesson2 = lessonRepository.findByName(lesson2.getName());

        lessonService.addLesson(course.getId(), lesson1);
        lessonService.addLesson(course.getId(), lesson2);
        Set<Lesson> result = lessonService.getLessonList(course.getId());
        Set<Lesson> ExpectedLessonSet = new HashSet<>();
        ExpectedLessonSet.add(lesson1);
        ExpectedLessonSet.add(lesson2);
        Assert.assertEquals(ExpectedLessonSet, result);

        lessonService.deleteLesson(course.getId(), lesson1.getId());
        result = lessonService.getLessonList(course.getId());
        ExpectedLessonSet.remove(lesson1);
        Assert.assertEquals(ExpectedLessonSet, result);
    }
}
