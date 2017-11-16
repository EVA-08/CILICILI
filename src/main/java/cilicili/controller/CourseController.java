package cilicili.controller;

import cilicili.domain.Course;
import cilicili.domain.User;
import cilicili.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * 课程相关控制器
 */
@Controller
public class CourseController {
    CourseService courseService;

    @Autowired
    private void setUserService(CourseService courseService) {
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
    public String course(Integer courseId, Model model, HttpSession httpSession) {
        User currentUser = (User) httpSession.getAttribute("currentUser");
        for (Course course : courseService.GetRegisteredCourseSet(currentUser.getId())) {
            if (course.getId().equals(courseId)) {
                model.addAttribute("registered", true);
                break;
            }
        }

        return "course";
    }


}
