package com.backend.domain.image.controller;

import com.backend.domain.image.dto.ImageRequest;
import com.backend.domain.image.service.ImageService;
import com.backend.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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
