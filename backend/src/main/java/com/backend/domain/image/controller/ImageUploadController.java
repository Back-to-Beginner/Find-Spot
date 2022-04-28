package com.backend.domain.image.controller;

import com.backend.domain.image.dto.ImageResponse;
import com.backend.domain.image.dto.ImageUploadRequest;
import com.backend.domain.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/api/v1/images")
@RestController
public class ImageUploadController {

    private final ImageService imageService;

    @PostMapping
    public ResponseEntity<ImageResponse> createImage(@RequestParam("images") MultipartFile multipartFile, @Validated @RequestBody ImageUploadRequest imageUploadRequest) throws IOException {
        return ResponseEntity.ok(ImageResponse
                .of(imageService
                        .saveImage(imageUploadRequest
                                .toEntity(multipartFile)
                        )
                )
        );
    }

    @GetMapping
    public ResponseEntity<List<ImageResponse>> findAllImage() {
        return ResponseEntity.ok(imageService
                .findAllImage()
                .stream()
                .map(ImageResponse::of)
                .collect(Collectors.toList())
        );
    }

    //TODO 로케이션 컨트롤러로 이동
    @GetMapping("/location/{id}/images")
    public ResponseEntity<List<ImageResponse>> findAllImageByLocationId(@PathVariable Long id) {
        return ResponseEntity.ok(imageService
                .findAllImageByLocationId(id)
                .stream()
                .map(ImageResponse::of)
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImageResponse> findOneImageById(@PathVariable Long id) {
        return ResponseEntity.ok(ImageResponse
                .of(imageService
                        .findImageById(id)
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteOneImageById(@PathVariable Long id) {
        imageService.deleteImageById(id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
}
