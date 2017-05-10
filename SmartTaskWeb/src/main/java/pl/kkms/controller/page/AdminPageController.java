package pl.kkms.controller.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.kkms.form.TaskForm;
import pl.kkms.gcm.NotificationService;
import pl.kkms.model.Task;
import pl.kkms.model.User;
import pl.kkms.repository.TaskRepository;
import pl.kkms.repository.UserRepository;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RequestMapping("admin/content")
@Controller
public class AdminPageController {

    private UserRepository userRepository;
    private TaskRepository taskRepository;

    @Autowired
    public AdminPageController(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    // TODO domyslnie przekierowuje od razu na zlecanie zadan, potem trzeba to rozbudowac

    @RequestMapping("/")
    public String redirectToCreateTask() {
        return "redirect:admin/content/createTask";
    }

    @RequestMapping(value = "/createTask")
    public String displayCreateTaskForm(TaskForm taskForm, Model model) {
        List<User> userList = userRepository.findAll();
        model.addAttribute("userList", userList);
        return "adminPage";
    }

    // TODO ujednolicic nazwy, dodac mozliwosc dodawania 1 zadania dla usera

    @RequestMapping(value = "/createTask", method = RequestMethod.POST)
    public String createTask(@Valid TaskForm taskForm, @RequestParam(required=false,name="save") String action, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "adminPage";
        }
        User user = userRepository.findByUserCode(taskForm.getUserCode());
        if(user == null) {
            redirectAttributes.addFlashAttribute("error", "Bledny indentyfikator uzytkownika!");
            return "redirect:/admin/content/createTask";
        }
        Task task = new Task();
        task.setName(taskForm.getTitle());
        task.setDescription(taskForm.getDescription());
        task.setBeginTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        task.setEndTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        task.setPriority(5);
        task.setStatus("Nierozpoczete");
        List<User> userList = new ArrayList<>();
        userList.add(user);
        task.setUsers(userList);
        taskRepository.save(task);

        if (action.equals("sendNow")) {
            NotificationService.sendPushNotification(taskForm.getTitle(), taskForm.getDescription());
        }

        redirectAttributes.addFlashAttribute("taskCreated", "Zlecono zadanie dla uzytkownika");
        return "redirect:/admin/content/createTask";
    }
}
