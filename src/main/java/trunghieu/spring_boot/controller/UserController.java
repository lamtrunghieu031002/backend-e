package trunghieu.spring_boot.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import trunghieu.spring_boot.entity.User;
import trunghieu.spring_boot.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    // lay toan bo danh sach user
    @GetMapping
    public ResponseEntity<List<User>>getAllUsers(){
        List<User> users=userRepository.findAll();

        // an password khi tra ve
        users.forEach(user -> user.setPassword(null));
        return ResponseEntity.ok(users);
    }

    // lay danh sach theo id

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> {

                    // ma hoa mat khau
                    user.setPassword(null);


                    return ResponseEntity.ok(user);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
