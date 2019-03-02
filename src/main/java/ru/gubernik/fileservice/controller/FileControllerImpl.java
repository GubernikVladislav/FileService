package ru.gubernik.fileservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.gubernik.fileservice.config.authentification.UserPrincipal;
import ru.gubernik.fileservice.model.File;
import ru.gubernik.fileservice.service.FileService;

/**
 * {@inheritDoc}
 */
@Controller
public class FileControllerImpl implements FileController{

    private final FileService fileService;

    @Autowired
    public FileControllerImpl(FileService fileService) {
        this.fileService = fileService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/upload")
    public String uploadFile(Model model, @RequestParam("file") MultipartFile file,
                             @RequestParam("description") String description,
                             @AuthenticationPrincipal UserPrincipal user){

        if(description == null || description.isEmpty()){
            model.addAttribute("message", "Введите описание файла");
            return "service";
        }

        if(file == null){
            model.addAttribute("message", "Выберите файл");
            return "service";
        }

        fileService.upload(file, description, user.getUser());
        model.addAttribute("message", "Файл загружен");
        model.addAttribute("files", fileService.fileList());

        return "service";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/download/{fileName}")
    public ResponseEntity<ByteArrayResource> download(@PathVariable("fileName") String fileName){

        File file = fileService.getByFileName(fileName);

        byte[] data = file.getFileData();
        ByteArrayResource resource = new ByteArrayResource(data);

        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getFileName())
                // Content-Type
                .contentType(MediaType.TEXT_PLAIN) //
                // Content-Lengh
                .contentLength(data.length) //
                .body(resource);
    }
}
