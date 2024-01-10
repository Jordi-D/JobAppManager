package com.jobs.jobapp.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class Utility {
    public static String saveFile(MultipartFile multiPart, String route) {
        // Get the original file name.
        String originalName = multiPart.getOriginalFilename();

        try {
            // Construct the file name to store it on the hard drive.
            File imageFile = new File(route + originalName);
            System.out.println("File: " + imageFile.getAbsolutePath());
            // Physically save the file on the hard drive.
            multiPart.transferTo(imageFile);

            return originalName;
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
            return null;
        }
    }
}
