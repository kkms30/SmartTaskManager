package pl.kkms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kkms.model.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    Task findByIdTask(int id);
}
