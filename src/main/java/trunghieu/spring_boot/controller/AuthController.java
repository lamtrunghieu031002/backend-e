package trunghieu.spring_boot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import trunghieu.spring_boot.entity.User;
import trunghieu.spring_boot.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;


@RestController
@RequestMapping("api/auth")

@RequiredArgsConstructor
public class AuthController {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    //  Đăng ký người dùng
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Email đã tồn tại!");
        }
        // Mã hoá mật khẩu
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Thiết lập role mặc định
        user.setRole("USER");



        // Gán thời gian tạo và cập nhật
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);


        return ResponseEntity.ok("Đăng ký thành công!");
    }
    //Đăng nhập người dùng
    @PostMapping("/login")
    public ResponseEntity<?>login(@RequestBody User loginRequest){
        Optional<User> optionalUser=userRepository.findByEmail(loginRequest.getEmail());
        if(optionalUser.isEmpty()){
            return ResponseEntity.badRequest().body("Email không tồn tại!");
        }
        User user=optionalUser.get();

        // So sánh mật khẩu
        if(!passwordEncoder.matches(loginRequest.getPassword(),user.getPassword())){
            return ResponseEntity.badRequest().body("Mật khẩu không đúng!");
        }
        // Xóa mật khẩu trước khi trả về
        user.setPassword(null);
        return ResponseEntity.ok(user);
    }


}
