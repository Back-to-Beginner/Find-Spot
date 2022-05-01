package com.backend.domain.image.controller;

import com.backend.domain.image.dto.ImageResponse;
import com.backend.domain.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/api/v1/images")
@RestController
public class ImageUploadController {

    private final ImageService imageService;

    @PostMapping
    public ResponseEntity<ImageResponse> createImage(@RequestParam("locationId") Long id, @RequestParam("images") MultipartFile multipartFile) throws IOException {
        return ResponseEntity.ok(ImageResponse
                .of(imageService
                        .saveImage(id, multipartFile)
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

    @GetMapping("/locations/{id}")
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
