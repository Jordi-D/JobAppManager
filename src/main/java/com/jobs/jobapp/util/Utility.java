package com.jobs.jobapp.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Utility class for file operations.
 */
public class Utility {

    /**
     * Saves a multipart file to the specified route.
     *
     * @param multiPart The multipart file to be saved.
     * @param route     The directory path where the file should be saved.
     * @return The original filename if the file was successfully saved; null otherwise.
     */
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
