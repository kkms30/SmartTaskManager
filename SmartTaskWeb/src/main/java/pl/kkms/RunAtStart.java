package pl.kkms;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import pl.kkms.repository.TaskRepository;
import pl.kkms.repository.UserRepository;

import javax.annotation.PostConstruct;

@Component
public class RunAtStart {
    private final Logger logger = Logger.getLogger(RunAtStart.class);
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public RunAtStart(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @PostConstruct
    public void runAtStart() {
//        List<User> users = userRepository.findAll();
//        users.forEach(u -> logger.info(u.getName()));

//        taskRepository.findAll().forEach(t -> logger.info(t.getName()));

//        Task task = taskRepository.findByIdTask(1);
//        logger.info(task.getName());

//        User user = userRepository.findByUserCode("637927");
//        logger.info(user.getName());

    }
}
