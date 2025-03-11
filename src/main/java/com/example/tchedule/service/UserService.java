package com.example.tchedule.service;

import com.example.tchedule.dto.RegisterRequestDto;
import com.example.tchedule.dto.LoginRequestDto;
import com.example.tchedule.model.User;
import com.example.tchedule.repository.UserRepository;
import com.example.tchedule.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil; // ✅ 추가된 부분

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil; // ✅ 추가된 부분
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User addUser(User users){
        return userRepository.save(users);
    }

    @Transactional
    public User register(RegisterRequestDto requestDto) {
        // 이메일 중복 체크
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());

        // User 객체 생성 후 저장
        User user = User.builder()
                .name(requestDto.getName())
                .email(requestDto.getEmail())
                .password(encodedPassword)
                .build();

        return userRepository.save(user);
    }

    public String login(LoginRequestDto requestDto) {
        Optional<User> optionalUser = userRepository.findByEmail(requestDto.getEmail());
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("가입되지 않은 이메일입니다.");
        }

        User user = optionalUser.get();
        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // ✅ 로그인 성공 시 JWT 토큰 반환
        return jwtUtil.generateToken(user.getEmail());
    }
}
