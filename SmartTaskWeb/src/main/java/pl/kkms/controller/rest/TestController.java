package pl.kkms.controller.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kkms.form.LoginForm;

/**
 * Created by Damian on 2017-05-06.
 */
@Controller
public class TestController {

    @RequestMapping(value = "/test")
    public String displayLoginForm() {
        return "testPage";
    }
}
