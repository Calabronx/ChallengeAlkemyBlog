import alkemy.challenge.Challenge.Alkemy.model.User;
import alkemy.challenge.Challenge.Alkemy.repository.IUser;
import alkemy.challenge.Challenge.Alkemy.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private IUser userRepository;

    @Test
    public void testProfileData() {
        //given the user data
        Optional<User> user = Optional.of(User.builder()
        .username("userTest")
        .password("passwordTest")
        .email("email")
        .build()
        );
        Mockito.when(userRepository.findByUsername("username"));
    }
}
