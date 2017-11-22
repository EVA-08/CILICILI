package cilicili.controller;

import cilicili.domain.Course;
import cilicili.domain.User;
import cilicili.service.CourseService;
import cilicili.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Set;


/**
 * 用户相关控制器
 */
@Controller
public class UserController {

    private UserService userService;
    private CourseService courseService;

    @Autowired
    private void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * 处理登录
     *
     * @param user 登录的用户信息，包括用户名和密码
     * @param httpSession httpSession对象
     * @return 登录结果，成功则返回"success"，失败则返回"fail"
     */
    @PostMapping(path = "/login")
    @ResponseBody
    public String login(User user, HttpSession httpSession) {
        boolean result = userService.login(user);
        if (result) {
            User currentUser = userService.getByUsername(user.getUsername());
            httpSession.setAttribute("currentUser", currentUser);
            return "success";
        } else {
            return "fail";
        }
    }

    /**
     * 用户登出
     *
     * @param httpSession httpSession对象
     * @return 首页页面
     */
    @GetMapping(path = "/logout")
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute("currentUser");
        return "redirect:/";
    }

    /**
     * 处理注册
     * @param user 注册信息
     * @param httpSession httpSession对象
     * @return 注册结果，成功为"success"，用户名已存在为"username_already_exist"，验证码错误为"wrong_capture"
     */
    @PostMapping(path = "/register")
    @ResponseBody
    public String register(User user, HttpSession httpSession) {
        UserService.RegisterResult result = userService.register(user);
        switch (result) {
            case SUCCESS: {
                User currentUser = userService.getByUsername(user.getUsername());
                httpSession.setAttribute("currentUser", currentUser);
                return "success";
            }

            case USERNAME_ALREADY_EXIST: {
                return "username_already_exist";
            }

            case WRONG_CAPTURE: {
                return "wrong_capture";
            }
        }
        return "username_already_exist";
    }

    /**
     * 注册一门课程
     *
     * @param user     当前用户
     * @param courseId 课程ID
     * @return 课程结构页面
     */
    @PostMapping(path = "/registerCourse/{courseId}")
    public String registerCourse(User user, @PathVariable Integer courseId) {
        return "";
    }

    /**
     * 根目录跳转到首页
     *
     * @param model 模型数据
     * @return 首页页面
     */
    @GetMapping(path = "/")
    public String index(Model model, HttpSession httpSession) {
        User currentUser = (User) httpSession.getAttribute("currentUser");
        if (currentUser != null) {
            model.addAttribute("user", currentUser);
            Set<Course> registeredCourses = courseService.GetRegisteredCourseSet(currentUser.getId());
            model.addAttribute("registeredCourses", registeredCourses);
        }
        Iterable<Course> courses = courseService.getAllCourse();
        model.addAttribute("courses", courses);
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
     * @param owner 更改的用户信息
     * @param httpSession HttpSession对象
     * @return 更改结果，成功为"success"，失败为"fail"
     */
    @PutMapping(path = "/change_personal_info")
    @ResponseBody
    public String changePersonalInfo(User owner, HttpSession httpSession) {
        User currentUser = (User) httpSession.getAttribute("currentUser");
        userService.changePersonalInfo(currentUser.getId(), owner);

        httpSession.setAttribute("currentUser", userService.getById(currentUser.getId()));
        return "success";
    }

    @GetMapping(path = "/login_register")
    public String loginRegister() {
        return "login_register";
    }

    /**
     * @param ownerId     拥有者ID
     * @param model       模型数据
     * @param httpSession HttpSession对象
     * @return
     */
    @GetMapping(path = "/personal/{ownerId}")
    public String personal(@PathVariable Integer ownerId, Model model, HttpSession httpSession) {
        User currentUser = (User) httpSession.getAttribute("currentUser");
        model.addAttribute("currentUser", currentUser);
        User owner = userService.getById(ownerId);
        model.addAttribute("owner", owner);
        return "personal";
    }

    @GetMapping(path = "/personal")
    public String personal(Model model, HttpSession httpSession) {
        User currentUser = (User) httpSession.getAttribute("currentUser");
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("owner", currentUser);
        return "personal";
    }

}
