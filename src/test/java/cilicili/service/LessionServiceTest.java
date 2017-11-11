package cilicili.service;

import cilicili.domain.Course;
import cilicili.domain.Lesson;
import cilicili.repository.CourseRepository;
import cilicili.repository.LessonRepository;
import cilicili.repository.QuestionRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class LessionServiceTest {
    private QuestionRepository questionRepository;
    private CourseRepository courseRepository;
    private LessonRepository lessonRepository;
    private LessonService lessonService;

    @Autowired
    private void setQuestionRepository(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    private void setCourseRepository(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    private void setLessonRepository(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    private void setLessonService(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @Test
    public void LessonTest() {
        Course course = new Course();
        course.setName("software engineering");
        course.setIntroduction("It is an useful course!");
        courseRepository.save(course);
        course = courseRepository.findByname(course.getName());

        Lesson lesson1 = new Lesson();
        lesson1.setName(" requirement analysis");
        lesson1.setDescription("It is the begining of a project");
        lesson1.setSequence(1);
        lessonRepository.save(lesson1);
        lesson1 = lessonRepository.findByName(lesson1.getName());

        Lesson lesson2 = new Lesson();
        lesson2.setName("system design");
        lesson2.setDescription("design a project generally");
        lesson2.setSequence(2);
        lessonRepository.save(lesson2);
        lesson2 = lessonRepository.findByName(lesson2.getName());

        lessonService.addLesson(course.getId(), lesson1);
        lessonService.addLesson(course.getId(), lesson2);
        Set<Lesson> result = lessonService.getlessonList(course.getId());
        Set<Lesson> Lessonset = new HashSet<>();
        Lessonset.add(lesson1);
        Lessonset.add(lesson2);
        Assert.assertEquals(Lessonset, result);

        lessonService.deleteLesson(course.getId(), lesson1.getId());
        result = lessonService.getlessonList(course.getId());
        Lessonset.remove(lesson1);
        Assert.assertEquals(Lessonset, result);
    }
}
