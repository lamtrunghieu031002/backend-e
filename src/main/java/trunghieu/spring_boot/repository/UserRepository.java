package trunghieu.spring_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trunghieu.spring_boot.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findBYUsername(String username);
    boolean existsByEmail(String email);
}
