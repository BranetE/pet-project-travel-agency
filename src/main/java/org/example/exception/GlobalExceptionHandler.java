package org.example.exception;

import com.mchange.util.AlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    public ModelAndView error404(Exception ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("statusCode", HttpStatus.NOT_FOUND);
        modelAndView.addObject("message", ex.getMessage());
        modelAndView.addObject("stackTrace", ex.getStackTrace().toString());
        return modelAndView;
    }

    @ExceptionHandler(RoomIsNotAvailableException.class)
    public String roomIsBusy(RoomIsNotAvailableException ex, RedirectAttributes redirAttrs){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("message", ex.getMessage());
        redirAttrs.addFlashAttribute("message", ex.getMessage());
        return "redirect:/order/book/" + ex.getRoom().getId();
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ModelAndView userAlreadyExists(AlreadyExistsException ex){
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }
}
