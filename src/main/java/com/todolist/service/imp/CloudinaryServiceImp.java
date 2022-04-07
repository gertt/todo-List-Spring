package com.todolist.service.imp;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import static com.todolist.util.AppConstants.URL;
import static com.todolist.util.ConvertMultipartFile.convertMultiPartToFile;

@Service
public class CloudinaryServiceImp {

    private final Cloudinary cloudinaryConfig;

    public CloudinaryServiceImp(Cloudinary cloudinaryConfig) {
        super();
        this.cloudinaryConfig = cloudinaryConfig;
    }

    @SneakyThrows
    public String uploadFile(MultipartFile multipartFile) {
        File file = convertMultiPartToFile(multipartFile);
        return cloudinaryConfig.uploader().upload(file, ObjectUtils.emptyMap()).get(URL).toString();
    }
}