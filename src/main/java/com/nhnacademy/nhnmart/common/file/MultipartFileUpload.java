package com.nhnacademy.nhnmart.common.file;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class MultipartFileUpload { private static final String FILE_UPLOAD_PATH = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/").toString();
    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList("gif", "jpg", "jpeg", "png");

    public String saveFile(MultipartFile multipartFile, String uploadDir) throws IOException {
        Path uploadPath = Paths.get(FILE_UPLOAD_PATH + uploadDir);
        String originalFileName = multipartFile.getOriginalFilename();

        if (originalFileName == null || originalFileName.isEmpty()) {
            throw new IOException("파일 이름이 없습니다.");
        }

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileExtension = getFileExtension(originalFileName);
        if (!ALLOWED_EXTENSIONS.contains(fileExtension.toLowerCase())) {
            throw new IOException("허용되지 않은 파일 형식입니다: " + fileExtension);
        }

        String safeFileName = generateSafeFileName(originalFileName);

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(safeFileName);
            Files.copy(inputStream, filePath);
        } catch (IOException e) {
            throw new IOException("파일 저장 중 오류 발생: " + e.getMessage(), e);
        }
        return safeFileName;
    }

    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }

    private String generateSafeFileName(String originalFileName) {
        String extension = getFileExtension(originalFileName);
        String baseName = originalFileName.substring(0, originalFileName.lastIndexOf("."))
                .replaceAll("[^a-zA-Z0-9.-]", "_");
        return baseName + "_" + UUID.randomUUID() + "." + extension;
    }
}
