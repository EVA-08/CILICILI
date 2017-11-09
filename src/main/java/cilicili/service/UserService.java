package cilicili.service;

import cilicili.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户相关业务
 */
@Service
@Transactional
public class UserService {


    /**
     * 更改密码
     *
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return 如果旧密码正确，则设置新密码并返回true，否则返回false
     */
    public boolean changePassword(String oldPassword, String newPassword) {
        return true;
    }

    /**
     * 更改个人信息
     *
     * @param user 用户个人信息
     */
    public void changePersonalInfo(User user) {

    }


}
