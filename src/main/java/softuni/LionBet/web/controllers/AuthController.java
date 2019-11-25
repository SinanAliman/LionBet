package softuni.LionBet.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import softuni.LionBet.web.models.RegisterUserModel;

@Controller
public class AuthController {

    @GetMapping("/register")
    public String registerForm(){
        return "auth/register.html";
    }

    @GetMapping("/login")
    public String loginForm(){
        return "auth/login.html";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute RegisterUserModel model){

        return "/";
    }
}
