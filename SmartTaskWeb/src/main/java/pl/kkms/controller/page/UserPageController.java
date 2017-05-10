package pl.kkms.controller.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.kkms.model.User;
import pl.kkms.repository.UserRepository;

@RequestMapping("user/content")
@Controller
public class UserPageController {

    private UserRepository userRepository;

    @Autowired
    public UserPageController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "qr/{userCode}", method = RequestMethod.GET)
    public String getQrCodeForUser(@PathVariable final String userCode, Model model) {
        User user = userRepository.findByUserCode(userCode);
        // TODO przeniesc stad nadawanie qrcode url (zapisywac do bazy?)
        user.setQrCodeUrl("/users/qr/" + user.getUserCode());
        model.addAttribute("user", user);
//        model.addAttribute("name", user.getName());
//        model.addAttribute("userCode", user.getUserCode());
        return "qrcodePage";
    }
}
