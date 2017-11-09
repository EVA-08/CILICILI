package cilicili.controller;

import cilicili.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;


/**
 * 用户相关控制器
 */
@Controller
@SessionAttributes("user")
public class UserController {

    private User user;

    /**
     * 处理登录
     *
     * @param user 登录的用户信息，包括用户名和密码
     * @return 登录结果，成功则返回"success"，失败则返回"fail"
     */
    @PostMapping(path = "/login")
    @ResponseBody
    public String login(User user) {
        return "";
    }

    /**
     * 处理注册
     *
     * @param user 注册信息
     * @return 注册结果，成功为"success"，失败为"fail"
     */
    @PostMapping(path = "/register")
    @ResponseBody
    public String register(User user) {
        return "";
    }

    /**
     * 注册一门课程
     *
     * @param user     当前用户
     * @param courseId 课程ID
     * @return 课程结构页面
     */
    @PostMapping(path = "/registerCourse/{courseId}")
    public String registerCourse(User user, Integer courseId) {
        return "";
    }

    /**
     * 根目录跳转到首页
     *
     * @param model 模型数据
     * @return 首页页面
     */
    @GetMapping(path = "/")
    public String index(Model model) {
        return "index";
    }

    /**
     * 获得相关数据，进入个人中心
     *
     * @param model 模型数据
     * @return 个人中心页面
     */
    @GetMapping(path = "/personal_center")
    public String personalCenter(Model model) {
        return "";
    }

    /**
     * 更改个人密码
     *
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return 更改结果，成功为"success"，失败为"fail"
     */
    @PostMapping(path = "/change_password")
    @ResponseBody
    public String changePassword(String oldPassword, String newPassword) {
        return "success";
    }


    /**
     * 更改个人信息
     *
     * @param user 更改的用户信息
     * @return 更改结果，成功为"success"，失败为"fail"
     */
    @PostMapping(path = "/change_personal_info")
    @ResponseBody
    public String changePersonalInfo(User user) {
        return "";
    }
}
