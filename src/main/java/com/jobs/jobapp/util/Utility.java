package com.jobs.jobapp.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class Utility {
    public static String saveFile(MultipartFile multiPart, String route) {
        // Obtenemos el nombre original del archivo.
        String originalName = multiPart.getOriginalFilename();

        try {
            // Formamos el nombre del archivo para guardarlo en el disco duro.
            File imageFile = new File(route + originalName);
            System.out.println("File: " + imageFile.getAbsolutePath());
            //Guardamos fisicamente el archivo en HD.
            multiPart.transferTo(imageFile);

            return originalName;
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
            return null;
        }
    }
}
