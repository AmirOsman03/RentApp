package mk.finki.ukim.mk.rent_v2.service;

import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;

public interface ImageService {
    String saveImage(MultipartFile imageFile) throws IOException;
}
