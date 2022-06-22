package com.example.bookmicroservices.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    String uploadFile(MultipartFile file, long id, String folder);
}
