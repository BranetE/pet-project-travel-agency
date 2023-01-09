package org.example.exception;

import com.mchange.util.AlreadyExistsException;
import org.dom4j.rule.Mode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    public ModelAndView error404(Exception ex) {
        ModelAndView modelAndView = new ModelAndView("error.html");
        modelAndView.addObject("statusCode", HttpStatus.NOT_FOUND);
        modelAndView.addObject("message", ex.getMessage());
        modelAndView.addObject("stackTrace", ex.getStackTrace().toString());
        return modelAndView;
    }

    @ExceptionHandler(RoomIsNotAvailableException.class)
    public ModelAndView roomIsBusy(RoomIsNotAvailableException ex){
        ModelAndView modelAndView = new ModelAndView("/orders/book/" + ex.getRoom().getId());
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ModelAndView userAlreadyExists(AlreadyExistsException ex){
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }
}
