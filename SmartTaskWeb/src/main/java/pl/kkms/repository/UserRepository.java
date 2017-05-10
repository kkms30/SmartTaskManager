package pl.kkms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kkms.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
    User findByIdUser(int id);
    User findByUserCode(String userCode);
    User findByUserCodeAndPassword(String UserCode, String password);
}
