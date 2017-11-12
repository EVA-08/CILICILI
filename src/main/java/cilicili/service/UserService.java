package cilicili.service;

import cilicili.domain.User;
import cilicili.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户相关业务
 */
@Service
@Transactional
public class UserService {
    private UserRepository userRepository;

    @Autowired
    private void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    /**
     * 更改密码
     * @param user 用户个人信息
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return 如果旧密码正确，则设置新密码并返回true，否则返回false
     */
    public boolean changePassword(User user, String oldPassword, String newPassword) {
        if (oldPassword.equals(user.getPassword()) == false)
            return false;
        else {
            User user1;
            user1 = userRepository.findOne(user.getId());
            user1.setPassword(newPassword);
            userRepository.save(user1);
            return true;
        }
    }

    /**
     * 更改个人信息
     *@param olduser 原用户个人信息
     * @param newuser 新用户个人信息
     */
    public void changePersonalInfo(User olduser, User newuser) {
        User user1;
        user1 = userRepository.findOne(olduser.getId());
        user1.setEmail(newuser.getEmail());
        userRepository.save(user1);
    }
    /**
     * 用户登录
     *
     * @param user 用户个人信息
     * @return 登录结果，如果登陆成功则返回true，否则返回false
     */
    public boolean login(User user) {
        if (userRepository.findByUsername(user.getUsername()) == null)
            return false;
        else {
            return userRepository.findByUsername(user.getUsername()).getPassword().equals(user.getPassword());
        }
    }

    /**
     * 用户注册
     *
     * @param user 用户个人信息
     * @return 注册结果, 如果用户名已存在，则返回USERNAME_ALREADY_EXIST，否则注册成功返回SUCCESS
     */
    public RegisterResult register(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null)
            return RegisterResult.USERNAME_ALREADY_EXIST;
        else {
            userRepository.save(user);
            return RegisterResult.SUCCESS;
        }
    }

    public enum RegisterResult {
        USERNAME_ALREADY_EXIST, SUCCESS, WRONG_CAPTURE
    }

    /**
     * 通过用户名得到user对象
     *
     * @param username 用户名
     * @return user 用户个人信息
     */
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * 通过用户id得到用户对象
     *
     * @param id 用户id
     * @return User 用户个人信息
     */
    public User getById(Integer id) {
        return userRepository.findOne(id);
    }
}
