package softuni.LionBet.web.controllers.handlers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionsHandler {


    @ExceptionHandler(Throwable.class)
    public ModelAndView handleException(Throwable exc){
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", exc.getMessage());
        return modelAndView;
    }
}
