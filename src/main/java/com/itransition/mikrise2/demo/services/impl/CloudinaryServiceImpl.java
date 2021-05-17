package com.itransition.mikrise2.demo.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.itransition.mikrise2.demo.services.CloudinaryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@AllArgsConstructor
@Service
public class CloudinaryServiceImpl implements CloudinaryService {
    private final Cloudinary cloudinaryConfig;

    public String uploadFile(MultipartFile file) {
        try {
            var uploadedFile = convertMultiPartToFile(file);
            var uploadResult = cloudinaryConfig.uploader().upload(uploadedFile, ObjectUtils.emptyMap());
            uploadedFile.delete();
            return uploadResult.get("url").toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        var convFile = new File(file.getOriginalFilename());
        var fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

}