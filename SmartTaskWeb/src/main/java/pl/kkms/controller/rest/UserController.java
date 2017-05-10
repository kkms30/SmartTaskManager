package pl.kkms.controller.rest;

import net.glxn.qrgen.QRCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kkms.model.Task;
import pl.kkms.model.User;
import pl.kkms.repository.TaskRepository;
import pl.kkms.repository.UserRepository;

import java.io.ByteArrayOutputStream;
import java.util.List;

@RequestMapping("users")
@RestController
public class UserController {

    private UserRepository userRepository;
    private TaskRepository taskRepository;

    @Autowired
    public UserController(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @GetMapping("all")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("{usercode}")
    public User findUserByUserCode(@PathVariable("usercode") String userCode) {
        return userRepository.findByUserCode(userCode);
    }

    // TODO zmienic z naglowka na obiekt ?
    @PutMapping("{idtask}/{status}")
    public Task changeTaskStatus(@PathVariable("idtask") int idTask,
                                 @PathVariable("status") String status) {
        Task task = taskRepository.findOne(idTask);
        task.setStatus(status);
        taskRepository.save(task);
        return task;
    }

    @RequestMapping(value = "qr/{userCode}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getQrCodeForUser(@PathVariable final String userCode) {
        ByteArrayOutputStream stream = QRCode.from(userRepository.findByUserCode(userCode).getUserCode()).withSize(250,250).stream();

        byte[] bytes = stream.toByteArray();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<>(bytes, headers, HttpStatus.CREATED);
    }
}
