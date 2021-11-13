package com.todolist.service.imp;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

import static com.todolist.util.AppConstants.*;
import static com.todolist.util.ConvertMultipartFile.convertMultiPartToFile;

@Service
public class CloudinaryServiceImp {

    @Autowired
    private Cloudinary cloudinaryConfig;

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    public String uploadFile(MultipartFile file) {
        File uploadedFile = convertMultiPartToFile(file);
        Map uploadResult = cloudinaryConfig.uploader().upload(uploadedFile, ObjectUtils.emptyMap());
        return SLASH.concat(uploadResult.get(VERSION).toString()
                .concat(SLASH.concat(uploadResult.get(PUBLIC_ID).toString()))
                .concat(DOT.concat(uploadResult.get(ORIGINAL_EXTENSION).toString())));
    }
}