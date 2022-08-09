package com.alevel.backend.service;

import com.alevel.backend.domain.response.DefaultResponse;
import com.alevel.backend.domain.response.ResponseMessage;
import com.alevel.backend.domain.response.StatusCode;
import com.alevel.backend.domain.user.User;
import com.alevel.backend.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    String encryptPassword;

    public DefaultResponse login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        //if (user.isEmpty()) {
        if (!user.isPresent()){ //자바8에서는 isEmpty 안지원,, 임시..
            return new DefaultResponse(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER, email);
        }
        if (user.get().getStatus()!=1) {
            return new DefaultResponse(StatusCode.UNAUTHORIZED, ResponseMessage.NOT_FOUND_USER, email);
        }
        if (!passwordEncoder.matches(password, user.get().getPassword())) {
            return new DefaultResponse(StatusCode.UNAUTHORIZED, ResponseMessage.LOGIN_FAIL, user);
        }
        return new DefaultResponse(user);
    }

    public User signup(String email, String password, String username) {
        User user = new User();
        encryptPassword = passwordEncoder.encode(password);
        user.setEmail(email);
        user.setPassword(encryptPassword);
        user.setUsername(username);
        userRepository.save(user);
        return user;
    }

    public void validateDuplicateUsername(String username) {
        userRepository.findByUsername(username)
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public void validateDuplicateEmail(String email) {
        userRepository.findByEmail(email)
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 이메일입니다.");
                });
    }

    public DefaultResponse remove(Long id) {
        Optional<User> userWrapper = userRepository.findById(id);
        User user = userWrapper.get();
        user.setStatus(0);
        userRepository.save(user);
        return new DefaultResponse(user);
    }

    public User updateUsername(Long id, String username) {
        User user = userRepository.getReferenceById(id);
        user.setUsername(username);
        return userRepository.save(user);
    }

    public User updatePassword(Long id, String password) {
        User user = userRepository.getReferenceById(id);
        encryptPassword = passwordEncoder.encode(password);
        user.setPassword(encryptPassword);
        return userRepository.save(user);
    }
}
