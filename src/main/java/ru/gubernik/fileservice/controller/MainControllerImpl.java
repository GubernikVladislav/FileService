package ru.gubernik.fileservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.gubernik.fileservice.config.authentification.UserPrincipal;
import ru.gubernik.fileservice.service.file.FileService;
import ru.gubernik.fileservice.service.user.UserService;

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

    /**
     * {@inheritDoc}
     */
    @Override
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
    @GetMapping("/userlist")
    public String userList(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model){

        if(model == null){
            throw new RuntimeException("Main controller model error");
        }

        if(userPrincipal == null || userPrincipal.getUser() == null){
            throw new RuntimeException("Authentication error: null user principal");
        }

        model.addAttribute("role", userPrincipal.getUser().getRole().getRole());
        model.addAttribute("users",userService.userList());

        return "userlist";
    }
}
