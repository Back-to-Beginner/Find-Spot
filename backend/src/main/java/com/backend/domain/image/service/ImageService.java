package com.backend.domain.image.service;

import com.backend.domain.image.domain.repository.JpaImageRepository;
import com.backend.domain.image.exception.ImageNotFoundException;
import com.backend.domain.image.domain.entity.Image;
import com.backend.domain.location.service.LocationService;
import com.backend.infra.aws.service.S3ImageUploader;
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
    private final JpaImageRepository imageRepository;
    private final S3ImageUploader imageUploader;
    private final LocationService locationService;

    public Image saveImage(Long id, MultipartFile multipartFile) throws IOException {
        return imageRepository.save(Image.builder()
                .location(locationService.findLocationById(id))
                .path(imageUploader.upload(multipartFile))
                .build()
        );
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
        findImageById(id).deleteImage();
    }
}
