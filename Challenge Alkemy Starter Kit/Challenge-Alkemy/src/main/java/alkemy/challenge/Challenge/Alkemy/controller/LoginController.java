package alkemy.challenge.Challenge.Alkemy.controller;

import alkemy.challenge.Challenge.Alkemy.exception.UserDoesNotExistException;
import alkemy.challenge.Challenge.Alkemy.model.User;
import alkemy.challenge.Challenge.Alkemy.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private IUserService userService;

    //log in the current user
    @GetMapping("/auth/login")
    public String login(Model model) {
       model.addAttribute("user", new User());
        return "login";
    }
    //save the current user in the database
    @PostMapping("/auth/sign_up")
    public String register(@ModelAttribute User user, BindingResult result,Model model) {
        //if there is errors return to the sing in
        if(result.hasErrors()) {
            return "redirect:/auth/sign_up";
        }
        //else save the user
        else {
            model.addAttribute("user", userService.register(user));
        }
        //return the user to the login if something unusual happens
        return "redirect:/auth/login";
    }
}
