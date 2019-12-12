package softuni.LionBet.web.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @GetMapping("/")
    @PreAuthorize("isAnonymous()")
    public String getIndex(HttpSession session){
        return "home/index.html";
    }

    @GetMapping("/home")
    @PreAuthorize("isAuthenticated()")
    public String getHome(){
        return "/home/home.html";
    }

    @GetMapping("/moderator")
    @PreAuthorize("isFullyAuthenticated()")
    public String getModeratorHome(){
        return "/home/moderator-home.html";
    }

}
