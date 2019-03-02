package ru.gubernik.fileservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.gubernik.fileservice.model.User;
import ru.gubernik.fileservice.service.UserService;

import javax.validation.Valid;

/**
 * {@inheritDoc}
 */
@Controller
public class MainControllerImpl implements MainController {

    private final UserService userService;

    @Autowired
    public MainControllerImpl(UserService userService) {
        this.userService = userService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/service")
    public String service(){

        return "service";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/registration")
    public String registration(User user){

        return "registration";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/registration")
    public String addUser(@Valid User user, BindingResult result){

        if (user == null){
            return "login";
        }

        if(result.hasErrors()){
            return "registration";
        }

        userService.addUser(user);

        return "redirect:/login";
    }
}
