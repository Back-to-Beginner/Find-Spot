package com.backend.controller;

import com.backend.component.ImageUploader;
import com.backend.component.S3Uploader;
import com.backend.entity.Image;
import com.backend.service.ImageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class ImageUploadController {

    private final ImageUploader imageUploader;
    private final ImageService imageService;

    public ImageUploadController(S3Uploader imageUploader, ImageService imageService) {
        this.imageUploader = imageUploader;
        this.imageService = imageService;
    }

    //TODO: 이미지가 일정 용량이상일 경우 업로드가 안되는데, 그 과정에서 이미지가 로컬에 저장은 되고 방치됌 -> 동일 파일 이름으로 이미지 업로드시 오류 유발
    @PostMapping("/images")
    public String images(@RequestParam("images") MultipartFile multipartFile) throws IOException {
        return imageUploader.upload(multipartFile);
    }

    @PostMapping("/uploads")
    public Long uploads(@RequestParam("images") MultipartFile multipartFile) throws IOException {
        Image image = new Image();
        image.setName("test");
        image.setPath(imageUploader.upload(multipartFile));
//        image.setLocation();
        return imageService.save(image);
    }
}
