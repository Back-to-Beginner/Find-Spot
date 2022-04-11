package com.backend.service;

import com.backend.component.S3Uploader;
import com.backend.entity.Image;
import repository.ImageRepository;
import repository.JpaImageRepository;

import javax.transaction.Transactional;

@Transactional
public class ImageService {
    private final ImageRepository imageRepository;
    private final S3Uploader s3Uploader;

    public ImageService(JpaImageRepository jpaImageRepository, S3Uploader s3Uploader) {
        this.imageRepository = jpaImageRepository;
        this.s3Uploader = s3Uploader;
    }

    public Long save(Image image) {
        return null;
    }
}
