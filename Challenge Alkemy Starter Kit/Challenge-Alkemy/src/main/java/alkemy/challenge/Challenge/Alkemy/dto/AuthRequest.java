package alkemy.challenge.Challenge.Alkemy.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.RestController;


@Data
public class AuthRequest {
    private String login;
    private String password;
}
