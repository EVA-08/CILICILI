package cilicili.service;

import cilicili.Application;
import cilicili.domain.User;
import cilicili.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(classes = Application.class)
public class UserServiceTest {
    private UserService userService;
    private UserRepository userRepository;

    @Autowired
    private void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    public void testRegister() {
        User user = new User();
        user.setUsername("aaa");
        user.setPassword("bbb");
        user.setIdentity(User.Identity.TEACHER);
        UserService.RegisterResult result = userService.register(user);
        Assert.assertEquals(UserService.RegisterResult.SUCCESS, result);

        User user1 = new User();
        user1.setUsername("aaa");
        user1.setPassword("LLL");
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

        boolean result = userService.changePassword(user, "asd", "bbb");
        Assert.assertEquals(false, result);
        User user1 = userRepository.findOne(user.getId());
        Assert.assertEquals("aaa", user1.getPassword());

        result = userService.changePassword(user, "aaa", "bbb");
        Assert.assertEquals(true, result);
        Assert.assertEquals("bbb", user1.getPassword());
    }
}
