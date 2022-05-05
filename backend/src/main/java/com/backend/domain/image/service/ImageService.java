package com.backend.domain.image.service;

import com.backend.domain.image.domain.repository.JpaImageRepository;
import com.backend.domain.image.domain.entity.Image;
import com.backend.domain.location.service.LocationService;
import com.backend.global.error.ErrorCode;
import com.backend.global.error.NotFoundException;
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
                .path(imageUploader.upload(multipartFile))
                .build()
        );
    }

    public List<Image> findAllImage() {
        return imageRepository.findAll();
    }

    public Image findImageById(Long id) {
        return imageRepository.findById(id).orElseThrow(
                ()-> new NotFoundException(ErrorCode.NOT_FOUND, "사진을 찾을 수 없습니다.")
        );
    }

    public void deleteImageById(Long id) {
        findImageById(id).deleteImage();
    }
}
