package four.controller;

import four.domain.User;
import four.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TestController {
    private TestService testService;

    @Autowired
    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    @GetMapping(path = "/")
    String home(Model model) {
        model.addAttribute("user", new User());
        return "test";
    }

    @PostMapping(path = "/test")
    String test(User user, Model model) {
        user = testService.saveUserAndReturnIt(user);
        model.addAttribute("user", user);
        return "test_result";
    }
}
