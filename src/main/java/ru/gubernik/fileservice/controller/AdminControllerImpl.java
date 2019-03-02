package ru.gubernik.fileservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.gubernik.fileservice.service.UserService;

/**
 * {@inheritDoc}
 */
@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminControllerImpl implements AdminController {

    private final UserService userService;

    @Autowired
    public AdminControllerImpl(UserService userService) {
        this.userService = userService;
    }

    /**
     * {@inheritDoc}
     */
    @GetMapping("/userlist")
    public String userList(Model model){
        model.addAttribute("users",userService.userList());

        return "userlist";
    }
}
