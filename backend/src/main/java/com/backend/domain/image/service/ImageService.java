package com.backend.domain.image.service;

import com.backend.domain.image.domain.repository.ImageJpaRepository;
import com.backend.domain.image.exception.ImageNotFoundException;
import com.backend.infra.aws.component.ImageUploader;
import com.backend.domain.image.domain.entity.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ImageService {
    private final ImageJpaRepository imageRepository;
    private final ImageUploader imageUploader;

    public Image saveImage(Image image) {
        return imageRepository.save(image);
    }

    public String uploadImage(MultipartFile multipartFile) throws IOException {
        return imageUploader.upload(multipartFile);
    }

    public List<Image> findAllImageByLocationId(Long location_id) {
        return imageRepository.findAllByLocationId(location_id);
    }

    public List<Image> findAllImage() {
        return imageRepository.findAll();
    }

    public Image findImageById(Long id) {
        return imageRepository.findById(id).orElseThrow(ImageNotFoundException::new);
    }

    public void deleteImageById(Long id) {
        imageRepository.findById(id).orElseThrow(ImageNotFoundException::new).deleteImage();
    }
}
