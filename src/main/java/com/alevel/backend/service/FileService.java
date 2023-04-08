package com.alevel.backend.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Service
public class FileService {

    @Value("${file.dir}")
    private String fileDir;

    public String getFullFilePath(String filename) {
        return fileDir + filename;
    }

    public String uploadFile(MultipartFile multipartFile) throws IOException {
        if(multipartFile.isEmpty()){
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename();
        String uploadFilename = createFileName(originalFilename);

        log.info("originalFilename={}", originalFilename);
        log.info("uploadFilename={}", uploadFilename);

        multipartFile.transferTo(new java.io.File(getFullFilePath(uploadFilename)));

        log.info("fullFilePath={}", getFullFilePath(uploadFilename));

        return getFullFilePath(uploadFilename);
    }

    private static String createFileName(String originalFilename) {
        String extension = extractedExtension(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + extension;
    }

    private static String extractedExtension(String originalFilename) {
        int position = originalFilename.lastIndexOf(".");
        return originalFilename.substring(position + 1);
    }
}
