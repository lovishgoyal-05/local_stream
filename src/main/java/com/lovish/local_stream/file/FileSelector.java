package com.lovish.local_stream.file;

import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import java.io.File;

public class FileSelector {

    public static File selectFile() {

        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setDialogTitle("Select a file to stream");

        fileChooser.setFileSelectionMode(
                JFileChooser.FILES_ONLY
        );

        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {

            File selectedFile =
                    fileChooser.getSelectedFile();

            System.out.println(
                    "Selected file: "
                            + selectedFile.getAbsolutePath()
            );

            return selectedFile;
        }

        System.out.println("No file selected");

        return null;
    }
}