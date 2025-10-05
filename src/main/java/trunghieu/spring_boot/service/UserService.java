package trunghieu.spring_boot.service;

import trunghieu.spring_boot.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {


    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    User createUser(User user);
    User updateUser(Long id, User updatedUser);
    void deleteUser(Long id);

}
