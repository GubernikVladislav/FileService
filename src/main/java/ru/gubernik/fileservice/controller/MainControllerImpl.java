package ru.gubernik.fileservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.gubernik.fileservice.config.authentification.UserPrincipal;
import ru.gubernik.fileservice.model.User;
import ru.gubernik.fileservice.service.FileService;
import ru.gubernik.fileservice.service.UserService;

import javax.validation.Valid;

/**
 * {@inheritDoc}
 */
@Controller
public class MainControllerImpl implements MainController {

    private final UserService userService;
    private final FileService fileService;

    @Autowired
    public MainControllerImpl(UserService userService, FileService fileService) {
        this.userService = userService;
        this.fileService = fileService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/service")
    public String service(@AuthenticationPrincipal UserPrincipal user, Model model){

        return "redirect:/service/" + user.getUsername();
    }

    @GetMapping("/service/{pageOwner}")
    public String userPage(@AuthenticationPrincipal UserPrincipal userPrincipal,
                           @PathVariable("pageOwner") String pageOwner, Model model){

        model.addAttribute("files", fileService.getUserFilesList(userPrincipal.getUsername()));
        model.addAttribute("role", userPrincipal.getUser().getRole().getRole());
        model.addAttribute("pageOwner", pageOwner);
        model.addAttribute("userName", userPrincipal.getUser().getUserName());

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
