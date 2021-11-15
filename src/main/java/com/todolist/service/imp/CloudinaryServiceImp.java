package com.todolist.service.imp;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import static com.todolist.util.AppConstants.*;
import static com.todolist.util.ConvertMultipartFile.convertMultiPartToFile;

@Service
public class CloudinaryServiceImp {

    @Autowired
    private Cloudinary cloudinaryConfig;

    @SneakyThrows
    public String uploadFile(MultipartFile file) {
        File uploadedFile = convertMultiPartToFile(file);
        return cloudinaryConfig.uploader().upload(uploadedFile, ObjectUtils.emptyMap()).get(URL).toString();
    }
}