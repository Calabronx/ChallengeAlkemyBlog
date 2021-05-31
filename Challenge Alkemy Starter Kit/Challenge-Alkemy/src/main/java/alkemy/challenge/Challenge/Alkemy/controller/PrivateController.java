package alkemy.challenge.Challenge.Alkemy.controller;

import alkemy.challenge.Challenge.Alkemy.exception.UserDoesNotExistException;
import alkemy.challenge.Challenge.Alkemy.model.User;
import alkemy.challenge.Challenge.Alkemy.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/private")
public class PrivateController {

    @Autowired
    private IUserService userService;

    @GetMapping("/index")
    public String index(Neo4jProperties.Authentication auth, HttpSession session) throws UserDoesNotExistException {
        String username = auth.getUsername();

        if (session.getAttribute("user") != null) {
            User user = userService.findByUsername(username);
            user.setPassword(user.getPassword());
            session.setAttribute("user", user);
        }
        return "index";
    }
}
