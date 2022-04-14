package com.backend.service;

import com.backend.component.ImageUploader;
import com.backend.component.S3Uploader;
import com.backend.entity.Image;
import com.backend.repository.ImageRepository;
import com.backend.repository.JpaImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class ImageService {
    private final ImageRepository imageRepository;
    private final ImageUploader imageUploader;

    public ImageService(JpaImageRepository imageRepository, S3Uploader imageUploader) {
        this.imageRepository = imageRepository;
        this.imageUploader = imageUploader;
    }

    public Long save(Image image) {
        imageRepository.save(image);
        return image.getId();
    }

    public String upload(MultipartFile multipartFile) throws IOException {
        return imageUploader.upload(multipartFile);
    }

    public List<Image> getImagesByLocationId(Long location_id) {
        return imageRepository.findByLocationId(location_id);
    }

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }
}
