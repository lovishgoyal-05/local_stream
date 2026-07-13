package com.lovish.local_stream.service;

import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class SelectFileService {

    private File selectedFile;

    public void setSelectedFile(File selectedFile) {
        this.selectedFile = selectedFile;
    }

    public File getSelectedFile() {
        return selectedFile;
    }

    public boolean hasSelectedFile() {
        return selectedFile != null
                && selectedFile.exists()
                && selectedFile.isFile();
    }
}