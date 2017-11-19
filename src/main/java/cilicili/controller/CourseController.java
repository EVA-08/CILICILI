package cilicili.controller;

import cilicili.domain.Course;
import cilicili.domain.User;
import cilicili.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 课程相关控制器
 */
@Controller
public class CourseController {
    private CourseService courseService;

    @Autowired
    private void setCourseService(CourseService courseService) {
        this.courseService = courseService;
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
}
