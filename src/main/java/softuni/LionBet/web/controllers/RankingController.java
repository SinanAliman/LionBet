package softuni.LionBet.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import softuni.LionBet.service.models.UserRankingServiceModel;
import softuni.LionBet.service.services.UserService;
import softuni.LionBet.web.models.UserRankingViewModel;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class RankingController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public RankingController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/ranking")
    public ModelAndView getRanking(@ModelAttribute ModelAndView modelAndView){
        List<UserRankingViewModel> users = this.userService.getAllUsers().stream().map(u -> this.modelMapper
                .map(u, UserRankingViewModel.class)).collect(Collectors.toList());

        modelAndView.addObject("users", users);
        modelAndView.setViewName("ranking");

        return modelAndView;
    }
}
