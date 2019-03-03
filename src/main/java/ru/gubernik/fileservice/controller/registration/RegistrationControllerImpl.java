package ru.gubernik.fileservice.controller.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.gubernik.fileservice.model.User;
import ru.gubernik.fileservice.service.user.UserService;

import javax.validation.Valid;

@Controller
public class RegistrationControllerImpl implements RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationControllerImpl(UserService userService) {
        this.userService = userService;
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

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/activation/{code}")
    public String activation(@PathVariable("code") String code) {

        if(code == null || code.isEmpty()){
            throw new RuntimeException("Activation code cannot be null");
        }

        userService.activateUser(code);

        return "redirect:/login";
    }


}
