package com.lovish.local_stream.Controller;

import com.lovish.local_stream.service.SelectFileService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.nio.file.Files;

import java.io.File;

@RestController
public class FileController {

    private final SelectFileService selectFileService;

    public FileController(
            SelectFileService selectFileService
    ) {
        this.selectFileService = selectFileService;
    }

    @GetMapping("/file-info")
    public String getFileInfo() {

        if (!selectFileService.hasSelectedFile()) {
            return "No file is currently selected.";
        }

        File file = selectFileService.getSelectedFile();

        return """
                File name: %s
                File size: %d bytes
                File path: %s
                """.formatted(
                file.getName(),
                file.length(),
                file.getAbsolutePath()
        );

    }
    @GetMapping("/stream")
    public ResponseEntity<Resource> streamFile() throws IOException {

        if (!selectFileService.hasSelectedFile()) {
            return ResponseEntity.notFound().build();
        }

        File file = selectFileService.getSelectedFile();

        Resource resource = new FileSystemResource(file);

        String contentType =
                Files.probeContentType(file.toPath());

        String fileName =
                file.getName().toLowerCase();

        if (fileName.endsWith(".mkv")) {

            contentType = "video/x-matroska";

        } else if (fileName.endsWith(".VOB")) {

            contentType = "video/mpeg";
        }

        if (contentType == null) {

            contentType =
                    MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"" + file.getName() + "\""
                )
                .contentType(
                        MediaType.parseMediaType(contentType)
                )
                .contentLength(file.length())
                .body(resource);
    }
}