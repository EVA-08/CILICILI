package cilicili.service;

import cilicili.Application;
import cilicili.domain.Info;
import cilicili.domain.User;
import cilicili.repository.InfoRepository;
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
public class UserServiceTest {
    private UserService userService;
    private UserRepository userRepository;
    private InfoRepository infoRepository;

    @Autowired
    private void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    private void setInfoRepository(InfoRepository infoRepository) {
        this.infoRepository = infoRepository;
    }
    @Test
    public void RegisterTest() {
        User user = new User();
        user.setUsername("bbb");
        user.setPassword("bbb");
        user.setIdentity(User.Identity.TEACHER);
        UserService.RegisterResult result = userService.register(user);
        Assert.assertEquals(UserService.RegisterResult.SUCCESS, result);

        User user1 = new User();
        user1.setUsername("bbb");
        user1.setPassword("ccc");
        user.setIdentity(User.Identity.TEACHER);
        result = userService.register(user1);
        Assert.assertEquals(UserService.RegisterResult.USERNAME_ALREADY_EXIST, result);
    }

    @Test
    public void changePasswordTest() {
        User user = new User();
        user.setPassword("aaa");
        user.setIdentity(User.Identity.STUDENT);
        user.setUsername("abc");
        userRepository.save(user);
        user = userRepository.findByUsername(user.getUsername());

        boolean result = userService.changePassword(user.getId(), "asd", "bbb");
        Assert.assertEquals(false, result);
        User user1 = userRepository.findOne(user.getId());
        Assert.assertEquals("aaa", user1.getPassword());

        result = userService.changePassword(user.getId(), "aaa", "bbb");
        Assert.assertEquals(true, result);
        user1 = userRepository.findOne(user.getId());
        Assert.assertEquals("bbb", user1.getPassword());
    }

    @Test
    public void changePersonalInfoTest() {
        User user1 = new User();
        user1.setIdentity(User.Identity.STUDENT);
        user1.setUsername("yanghuan");
        user1.setPassword("123");
        user1.setEmail("987654321@qq.com");
        userRepository.save(user1);
        user1 = userRepository.findByUsername(user1.getUsername());

        User user2 = new User();
        user2.setEmail("123456789@qq.com");
        user2.setUsername("yanghuan");
        user2.setPassword("123");
        user2.setIdentity(User.Identity.STUDENT);


        Assert.assertEquals("987654321@qq.com", user1.getEmail());
        userService.changePersonalInfo(user1.getId(), user2);
        Assert.assertEquals("123456789@qq.com", user1.getEmail());
    }

    @Test
    public void InfoTest() {
        User user = new User();
        user.setIdentity(User.Identity.TEACHER);
        user.setUsername("ZhangHao");
        user.setPassword("123456");
        userRepository.save(user);

        Info educationInfo = new Info();
        educationInfo.setDescription("JiLin University");
        infoRepository.save(educationInfo);
        userService.addEducationInfo(user.getId(), educationInfo);

        Info teachingInfo = new Info();
        teachingInfo.setDescription("Software Engineering");
        infoRepository.save(teachingInfo);
        userService.addTeachingInfo(user.getId(), teachingInfo);

        Info awardInfo = new Info();
        awardInfo.setDescription("AAAAAAAAAAA");
        infoRepository.save(awardInfo);
        userService.aadAwardInfo(user.getId(), awardInfo);

        Info aphorismInfo = new Info();
        aphorismInfo.setDescription("BBBBBBBBB");
        infoRepository.save(aphorismInfo);
        user.setAphorism(aphorismInfo);

        Set<Info> result = userService.getEducationSet(user.getId());
        Set<Info> expectedEducationSet = new HashSet<>();
        expectedEducationSet.add(educationInfo);
        Assert.assertEquals(expectedEducationSet, result);

        result = userService.getTeachingSet(user.getId());
        Set<Info> expectedTeachingSet = new HashSet<>();
        expectedTeachingSet.add(teachingInfo);
        Assert.assertEquals(expectedTeachingSet, result);

        result = userService.getAwardSet(user.getId());
        Set<Info> expectedAwardSet = new HashSet<>();
        expectedAwardSet.add(awardInfo);
        Assert.assertEquals(expectedAwardSet, result);

        Info result1 = userService.getAphorism(user.getId());
        Assert.assertEquals(aphorismInfo, result1);
    }
}
