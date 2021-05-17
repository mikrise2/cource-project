package com.itransition.mikrise2.demo.services;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {

    String uploadFile(MultipartFile file);

}
