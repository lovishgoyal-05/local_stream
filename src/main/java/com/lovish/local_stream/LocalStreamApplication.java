package com.lovish.local_stream;

import com.lovish.local_stream.file.FileSelector;
import com.lovish.local_stream.service.SelectFileService;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;

@SpringBootApplication
public class LocalStreamApplication {

	public static void main(String[] args) {

		System.setProperty(
				"java.awt.headless",
				"false"
		);

		ConfigurableApplicationContext context =
				SpringApplication.run(
						LocalStreamApplication.class,
						args
				);

		File selectedFile = FileSelector.selectFile();

		if (selectedFile != null) {

			SelectFileService selectedFileService =
					context.getBean(
							SelectFileService.class
					);

			selectedFileService.setSelectedFile(
					selectedFile
			);

			System.out.println(
					"File ready to stream: "
							+ selectedFile.getAbsolutePath()
			);
		}
	}
}