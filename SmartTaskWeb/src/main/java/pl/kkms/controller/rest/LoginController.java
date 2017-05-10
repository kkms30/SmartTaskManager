package pl.kkms.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.kkms.form.LoginForm;
import pl.kkms.model.User;
import pl.kkms.repository.UserRepository;

import javax.validation.Valid;

@Controller
public class LoginController {

    private UserRepository userRepository;

    @Autowired
    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/")
    public String redirectToLoginForm() {
        return "redirect:/login";
    }

    @RequestMapping(value = "/login")
    public String displayLoginForm(LoginForm loginForm, Model model) {
        return "loginPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@Valid LoginForm loginForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "loginPage";
        }
        User user = userRepository.findByUserCodeAndPassword(loginForm.getLogin(), loginForm.getPassword());
        if (user == null) {
            redirectAttributes.addFlashAttribute("error", "Bledny identyfikator lub haslo");
            return "redirect:login";
        }

        // TODO wywalic po przerobieniu bazy
        if(user.getUserCode().equals("111111")){
            return "redirect:admin/content/createTask";
        }
        return "redirect:user/content/qr/" + user.getUserCode();
    }
}
