package ru.gubernik.fileservice.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.gubernik.fileservice.service.file.FileService;
import ru.gubernik.fileservice.service.user.UserService;

/**
 * {@inheritDoc}
 */
@Controller
@PreAuthorize("hasAnyAuthority('ADMIN', 'ANALYST')")
public class AdminControllerImpl implements AdminController {

    private final UserService userService;
    private final FileService fileService;

    @Autowired
    public AdminControllerImpl(UserService userService, FileService fileService) {
        this.userService = userService;
        this.fileService = fileService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/files")
    public String fileList(Model model){

        if (model == null){
            throw new RuntimeException("Admin page model error");
        }

        model.addAttribute("files", fileService.fileList());

        return "filelist";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/statistic")
    public String statistic(Model model) {

        if (model == null){
            throw new RuntimeException("Admin page model error");
        }

        model.addAttribute("userCount", userService.userList().size());
        model.addAttribute("fileCount", fileService.fileList().size());

        return "statistic";
    }
}
