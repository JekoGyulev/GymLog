package com.example.gymlog.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@Slf4j
public class FileStorageService {

    private final Path uploadDir = Paths.get("uploads");

    public String save(MultipartFile image) {

        try {
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();

            Path filePath = uploadDir.resolve(fileName);

            image.transferTo(filePath);

            String imageURL = "/uploads/" +  fileName;

            return imageURL;

        } catch (IOException e) {
            log.error("Failed to save image", e);
            throw new RuntimeException("Failed to save image", e);
        }
    }

    public void delete(String oldImageURL) {
        String fileName = Paths.get(oldImageURL).getFileName().toString();

        Path path = uploadDir.resolve(fileName);

        try {
            Files.deleteIfExists(path);
        }  catch (IOException e) {
            log.error("Failed to delete image: {}", path, e);
            throw new RuntimeException("Failed to delete image", e);
        }
    }
}
