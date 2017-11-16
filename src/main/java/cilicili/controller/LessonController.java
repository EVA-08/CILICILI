package cilicili.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 课程相关控制器
 */
@Controller
public class LessonController {
    /**
     * 获得相关数据，进入一节课
     *
     * @param courseId    课程ID
     * @param lessonOrder 一节课的序号
     * @return 进入一节课页面
     */
    @GetMapping(path = "/course/{courseId}/lesson/{lessonOrder}")
    public String lesson(@PathVariable Integer courseId, @PathVariable Integer lessonOrder) {
        return "";
    }

    @GetMapping(path = "testLesson")
    public String testLesson() {
        return "lesson";
    }
}
