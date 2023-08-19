package com.example.emailsecurityassessment.user;

import com.example.emailsecurityassessment.message.Message;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    public static final String SENDER_EMAIL = "testUserEmail@gmail.com";

    @Mock
    private UserRepository userRepository;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldAddMessageToUserWhenUserExist() {
        User user = new User();
        user.setEmail(SENDER_EMAIL);
        Message message = new Message();
        when(userRepository.findFirstByEmail(SENDER_EMAIL)).thenReturn(user);

        userService.addUser(SENDER_EMAIL, message);

        verify(userRepository).findFirstByEmail(SENDER_EMAIL);
        verify(userRepository).save(any(User.class));
        assertThat(user.getMessage()).contains(message);
    }

    @Test
    void shouldAddUserWhenUserNotExist() {
        when(userRepository.findFirstByEmail(SENDER_EMAIL)).thenReturn(null);
        Message message = new Message();

        userService.addUser(SENDER_EMAIL, message);

        verify(userRepository).findFirstByEmail(SENDER_EMAIL);
        verify(userRepository).save(userCaptor.capture());
        assertThat(userCaptor.getValue().getEmail()).isEqualTo(SENDER_EMAIL);
        assertThat(userCaptor.getValue().getMessage()).containsOnly(message);
    }
}

