package cilicili.repository;

import cilicili.domain.Course;
import cilicili.domain.Lesson;
import cilicili.domain.Resource;
import cilicili.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepositoryTest {
    private UserRepository userRepository;
    private AnswerRepository answerRepository;
    private BarrageRepository barrageRepository;
    private CourseRepository courseRepository;
    private PublishedHomeworkRepository publishedHomeworkRepository;
    private LessonRepository lessonRepository;
    private QuestionRepository questionRepository;
    private ResourceRepository resourceRepository;
    private VideoRepository videoRepository;
    private TestEntityManager entityManager;

    public TestEntityManager getEntityManager() {
        return entityManager;
    }

    @Autowired
    public void setEntityManager(TestEntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AnswerRepository getAnswerRepository() {
        return answerRepository;
    }

    @Autowired
    public void setAnswerRepository(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public BarrageRepository getBarrageRepository() {
        return barrageRepository;
    }

    @Autowired
    public void setBarrageRepository(BarrageRepository barrageRepository) {
        this.barrageRepository = barrageRepository;
    }

    public CourseRepository getCourseRepository() {
        return courseRepository;
    }

    @Autowired
    public void setCourseRepository(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public PublishedHomeworkRepository getPublishedHomeworkRepository() {
        return publishedHomeworkRepository;
    }

    @Autowired
    public void setPublishedHomeworkRepository(PublishedHomeworkRepository publishedHomeworkRepository) {
        this.publishedHomeworkRepository = publishedHomeworkRepository;
    }

    public LessonRepository getLessonRepository() {
        return lessonRepository;
    }

    @Autowired
    public void setLessonRepository(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public QuestionRepository getQuestionRepository() {
        return questionRepository;
    }

    @Autowired
    public void setQuestionRepository(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public ResourceRepository getResourceRepository() {
        return resourceRepository;
    }

    @Autowired
    public void setResourceRepository(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public VideoRepository getVideoRepository() {
        return videoRepository;
    }

    @Autowired
    public void setVideoRepository(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @Test
    public void testUserRepository() throws Exception {
        User user = new User();
        user.setUsername("Jack");
        user.setPassword("4399");
        user.setEmail("1231231@qq.com");
        user.setIdentity(User.Identity.TEACHER);
        entityManager.persist(user);
        Assert.assertNotNull(user.getId());
        User user1 = userRepository.findOne(user.getId());
        Assert.assertEquals(user, user1);
        System.out.println(user.getId());
    }

    @Test
    public void testUserCourseRelationship() {
        User user1 = new User();
        user1.setUsername("Beep");
        user1.setPassword("4444");
        user1.setEmail("1231231@qq.com");
        user1.setIdentity(User.Identity.STUDENT);

        User user2 = new User();
        user2.setUsername("ppp");
        user2.setPassword("csm");
        user2.setEmail("43323423@qq.com");
        user2.setIdentity(User.Identity.STUDENT);

        Course course1 = new Course();
        course1.setIntroduction("");
        course1.setName("第一个");

        Course course2 = new Course();
        course2.setIntroduction("");
        course2.setName("第二个");

        user1.getRegisteredCourseSet().add(course1);
        user1.getRegisteredCourseSet().add(course2);
        user2.getRegisteredCourseSet().add(course1);
        user2.getRegisteredCourseSet().add(course2);
        course1.getRegisteredUserSet().add(user1);
        course1.getRegisteredUserSet().add(user2);
        course2.getRegisteredUserSet().add(user1);
        course2.getRegisteredUserSet().add(user2);
        userRepository.save(user1);
        userRepository.save(user2);

        Course course = courseRepository.findOne(course1.getId());

        Assert.assertTrue(!course.getRegisteredUserSet().isEmpty());
        Assert.assertEquals(2, course.getRegisteredUserSet().size());
    }

    @Test
    public void testLessonResourceRelationship() {
        Lesson lesson = new Lesson();
        Resource resource = new Resource();
        resource.setName("好资源");
        lesson.addResource(resource);

        lessonRepository.save(lesson);
        lesson = lessonRepository.findOne(lesson.getId());
        Assert.assertEquals(1, lesson.getResourceSet().size());
    }
}
