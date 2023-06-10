package com.example.emailsecurityassessment.user;

import com.example.emailsecurityassessment.message.Message;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(String senderEmail, Message message) {
        if (userRepository.existsByEmail(senderEmail)) {
            User user = userRepository.findFirstByEmail(senderEmail);
            user.addMessage(message);
            userRepository.save(user);
            System.out.println("User already exist: " + senderEmail);
        } else {
            User user = new User();
            user.setEmail(senderEmail);
            user.addMessage(message);
            userRepository.save(user);
        }
    }
}
