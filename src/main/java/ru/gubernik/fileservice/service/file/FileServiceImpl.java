package ru.gubernik.fileservice.service.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.gubernik.fileservice.dao.file.FileDao;
import ru.gubernik.fileservice.dao.user.UserDao;
import ru.gubernik.fileservice.model.File;
import ru.gubernik.fileservice.model.User;

import java.io.IOException;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class FileServiceImpl implements FileService {

    private final FileDao fileDao;
    private final UserDao userDao;

    @Autowired
    public FileServiceImpl(FileDao fileDao, UserDao userDao) {
        this.fileDao = fileDao;
        this.userDao = userDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void upload(MultipartFile uploadFile, String description, User owner) {

        if(uploadFile == null || description == null || description.isEmpty() || owner == null){
            return;
        }

        User user = userDao.getUserByName(owner.getUserName());

        File file = new File();
        file.setDescription(description);
        file.setUser(user);
        file.setFileName(uploadFile.getOriginalFilename());
        try {
            file.setFileData(uploadFile.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        fileDao.saveFile(file);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<File> fileList() {
        return fileDao.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public File getByFileName(String fileName) {

        if(fileName == null || fileName.isEmpty()){
            throw  new RuntimeException("File service error: file name cannot be null or empty");
        }

        return fileDao.findByFileName(fileName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<File> getUserFilesList(String userName) {

        if(userName == null || userName.isEmpty()){
            throw  new RuntimeException("File service error: user name cannot be null or empty");
        }

        User user = userDao.getUserByName(userName);
        return fileDao.findUserFiles(user);
    }
}
