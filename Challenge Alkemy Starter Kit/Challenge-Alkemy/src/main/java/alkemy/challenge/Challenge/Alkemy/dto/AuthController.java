package alkemy.challenge.Challenge.Alkemy.dto;

import alkemy.challenge.Challenge.Alkemy.exception.UserDoesNotExistException;
import alkemy.challenge.Challenge.Alkemy.model.User;
import alkemy.challenge.Challenge.Alkemy.security.jwt.JwtProvider;
import alkemy.challenge.Challenge.Alkemy.service.PostService;
import alkemy.challenge.Challenge.Alkemy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private PostService postService;

    //manage the registration request authentication
    @PostMapping("/sign_up")
    public String registerUser(@RequestBody @Validated RegistrationRequest registrationRequest) {
        User user = new User();
        user.setPassword(registrationRequest.getPassword());
        user.setUsername(registrationRequest.getLogin());
        userService.register(user);
        return "sign_up";
    }

    @GetMapping("/sign_up")
    public String registerForm(Model model) throws UserDoesNotExistException {
        model.addAttribute("user", new User());
        return "sign_up";
    }

    //manage the registered user int hte login
    @PostMapping("/auth/login")
    public AuthResponse auth(@RequestBody AuthRequest request) throws UserDoesNotExistException {
        //find the user and request the log ing and password
        User user = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        //generates token with jwtProvider with fields
        String token = jwtProvider.generateToken(user.getUsername());
        return new AuthResponse(token);
    }

}
