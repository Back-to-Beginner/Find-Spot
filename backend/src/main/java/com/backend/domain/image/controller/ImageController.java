package com.backend.domain.image.controller;

import com.backend.domain.image.dto.ImageRequest;
import com.backend.domain.image.service.ImageService;
import com.backend.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.backend.global.dto.ApiResponse.*;
import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/images")
@RestController
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload")
    @ResponseStatus(CREATED)
    public ApiResponse uploadImage(
            @RequestParam("id") Long id,
            @RequestParam("images") MultipartFile multipartFile
    ) throws IOException {
        return created(
                imageService.uploadImage(id, multipartFile)
        );
    }

    @GetMapping("/compare")
    @ResponseStatus(CREATED)
    public ApiResponse compareImage(
            @RequestParam("challengeId") Long challengeId,
            @RequestParam("missionId") Long missionId
    ) throws IOException {
        return ok(
                imageService.compareImage(challengeId, missionId)
        );
    }

    @GetMapping("/test")
    public ResponseEntity testConnection() {
        RestTemplate restTemplate = new RestTemplate();
        return ResponseEntity.ok(
                restTemplate.getForObject("http://image-analysis:5001/api-python/v1/test", String.class)
        );
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ApiResponse createImage(
            @RequestBody @Validated ImageRequest request
    ) {
        return created(imageService.save(request));
    }

    @GetMapping
    @ResponseStatus(OK)
    public ApiResponse findAllImage() {
        return ok(imageService.findAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public ApiResponse findOneImageById(
            @PathVariable Long id
    ) {
        return ok(imageService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public ApiResponse deleteOneImageById(
            @PathVariable Long id
    ) {
        imageService.deleteById(id);
        return noContent();
    }
}
