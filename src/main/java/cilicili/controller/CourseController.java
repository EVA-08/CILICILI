package cilicili.controller;

import cilicili.domain.Course;
import cilicili.domain.User;
import cilicili.service.CourseService;
import cilicili.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * 课程相关控制器
 */
@Controller
public class CourseController {
    private CourseService courseService;
    private UserService userService;

    @Autowired
    private void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 获得相关数据，进入课程信息页面
     *
     * @param courseId 课程ID
     * @param model    模型数据
     * @param httpSession HttpSession对象
     * @return 课程信息页面
     */
    @GetMapping(path = "/course/{courseId}")
    public String course(@PathVariable Integer courseId, Model model, HttpSession httpSession) {
        User currentUser = (User) httpSession.getAttribute("currentUser");
        boolean registered = courseService.GetRegisteredCourseSet(currentUser.getId())
                .stream()
                .anyMatch(course -> courseId.equals(course.getId()));
        model.addAttribute("registered", registered);
        Course currentCourse = courseService.getCourse(courseId);
        model.addAttribute("currentCourse", currentCourse);
        return "course";
    }

    /**
     * 注册课程
     *
     * @param courseId    课程ID
     * @param httpSession HttpSession对象
     * @return 注册是否成功，成功为success,否则为fail
     */
    @PostMapping(path = "/course/{courseId}/register")
    @ResponseBody
    public String registerCourse(@PathVariable Integer courseId, HttpSession httpSession) {
        User currentUser = (User) httpSession.getAttribute("currentUser");
        courseService.registerCourse(currentUser.getId(), courseId);
        return "success";
    }

    /**
     * 退选课程
     *
     * @param courseId    课程ID
     * @param httpSession HttpSession对象
     * @return 退选是否成功，成功为success,否则为fail
     */
    @PostMapping(path = "/course/{courseId}/unregister")
    @ResponseBody
    public String unregisterCourse(@PathVariable Integer courseId, HttpSession httpSession) {
        User currentUser = (User) httpSession.getAttribute("currentUser");
        courseService.unregisterCourse(currentUser.getId(), courseId);
        return "success";
    }

    /**
     * 创建课程
     *
     * @param name         课程名称
     * @param introduction 课程介绍
     * @param image        上传图像
     * @param httpSession  HttpSession对象
     * @return 成功为"success"
     */
    @PostMapping(path = "/course")
    public String createCourse(@RequestParam String name, @RequestParam String introduction, @RequestParam MultipartFile image, HttpSession httpSession) {
        User author = (User) httpSession.getAttribute("currentUser");
        Course course = new Course();
        course.setName(name);
        course.setIntroduction(introduction);
        courseService.createCourse(course, author, image);
        return "redirect:/course_manager";
    }

    /**
     * 获得课程信息
     *
     * @param courseId 课程ID
     * @return 课程对象
     */
    @GetMapping(path = "/course_info/{courseId}")
    @ResponseBody
    public Course getCourseInfo(@PathVariable Integer courseId) {
        Course course = courseService.getCourse(courseId);
        course.setAuthor(null);
        course.setRegisteredUserSet(null);
        return course;
    }

    /**
     * 更新课程信息
     *
     * @param id           课程ID
     * @param name         课程名
     * @param introduction 课程介绍
     * @param image        上传图片
     * @param httpSession  HttpSession对象
     * @return 返回课程管理页面
     */
    @PostMapping(path = "/change_course")
    public String updateCourse(@RequestParam Integer id, @RequestParam String name, @RequestParam String introduction, @RequestParam MultipartFile image, HttpSession httpSession) {
        Course course = new Course();
        course.setId(id);
        course.setName(name);
        course.setIntroduction(introduction);
        courseService.updateCourse(course, (User) httpSession.getAttribute("currentUser"), image);
        return "redirect:/course_manager";
    }

    @GetMapping(path = "/course_manager")
    public String getCourseManager(Model model, HttpSession httpSession) {
        User currentUser = (User) httpSession.getAttribute("currentUser");
        model.addAttribute("currentUser", currentUser);
        return "course_manager";
    }

    @DeleteMapping(path = "/course")
    @ResponseBody
    public String deleteCourse(@RequestParam Integer courseId, HttpSession httpSession) {
        courseService.deleteCourse(courseId);
        User currentUser = (User) httpSession.getAttribute("currentUser");
        httpSession.setAttribute("currentUser", userService.getById(currentUser.getId()));
        return "success";
    }
}
