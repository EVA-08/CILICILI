package cilicili.controller;

import cilicili.domain.Lesson;
import cilicili.domain.User;
import cilicili.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

/**
 * 课程相关控制器
 */
@Controller
public class LessonController {
    private LessonService lessonService;

    @Autowired
    private void setLessonService(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    /**
     * 获得相关数据，进入一节课
     *
     * @param courseId    课程ID
     * @param lessonSequence 一节课的序号
     * @param model 模型数据
     * @param httpSession HttpSession对象
     * @return 进入一节课页面
     */
    @GetMapping(path = "/course/{courseId}/lesson/{lessonSequence}")
    public String lesson(@PathVariable Integer courseId, @PathVariable Integer lessonSequence, Model model, HttpSession httpSession) {
        Lesson currentLesson = lessonService.findByCourseIdAndSequence(courseId, lessonSequence);
        User currentUser = (User) httpSession.getAttribute("currentUser");
        model.addAttribute("currentLesson", currentLesson);
        model.addAttribute("currentUser", currentUser);
        return "lesson";
    }

    /**
     * 获得一节课信息
     *
     * @param lessonId 一节课Id
     * @return 返回一节课信息
     */
    @GetMapping(path = "/lesson/{lessonId}")
    public Lesson getLesson(@PathVariable Integer lessonId) {
        return null;
    }
}
