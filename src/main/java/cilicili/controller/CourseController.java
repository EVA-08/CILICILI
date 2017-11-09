package cilicili.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 课程相关控制器
 */
@Controller
public class CourseController {

    /**
     * 获得相关数据，进入课程信息页面
     *
     * @param courseId 课程ID
     * @param model    模型数据
     * @return 课程信息页面
     */
    @GetMapping(path = "/course/{courseId}")
    public String course(Integer courseId, Model model) {
        return "";
    }

    /**
     * 获得相关数据，进入课程结构页面
     *
     * @param courseId 课程ID
     * @param model    模型数据
     * @return 课程结构页面
     */
    @GetMapping(path = "/course/{courseId}/structure")
    public String courseStructure(Integer courseId, Model model) {
        return "";
    }

}
