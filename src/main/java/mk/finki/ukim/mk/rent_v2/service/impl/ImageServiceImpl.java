package mk.finki.ukim.mk.rent_v2.service.impl;

import mk.finki.ukim.mk.rent_v2.service.ImageService;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ImageServiceImpl implements ImageService {

    private static final String UPLOAD_DIR = "src/main/resources/static/images";;

    @Override
    public String saveImage(MultipartFile imageFile) throws IOException {
        // Create the directory if it does not exist
        File dir = new File(UPLOAD_DIR);
        if (!dir.exists()) {
            boolean created = dir.mkdirs();  // Make sure the directory is created
            if (!created) {
                throw new IOException("Failed to create directory for image storage.");
            }
        }

        // Get the original filename
        String originalFilename = imageFile.getOriginalFilename();
        if (originalFilename == null || originalFilename.isEmpty()) {
            throw new IOException("Invalid image file.");
        }

        // Ensure a unique filename by appending a timestamp (avoiding overwriting)
        String uniqueFilename = System.currentTimeMillis() + "_" + originalFilename;

        // Generate the path where the image will be saved
        String imagePath = UPLOAD_DIR + "/" + uniqueFilename;

        // Save the image to the directory
        Files.copy(imageFile.getInputStream(), Paths.get(imagePath));

        // Return the relative path that can be saved in the database
        return "/images/" + uniqueFilename;
    }
}
