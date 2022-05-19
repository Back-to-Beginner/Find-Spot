package com.backend.domain.image.controller;

import com.backend.domain.image.dto.ImageResponse;
import com.backend.domain.image.service.ImageService;
import com.backend.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Collectors;

import static com.backend.global.dto.ApiResponse.*;
import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/images")
@RestController
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/moment")
    @ResponseStatus(CREATED)
    public ApiResponse createImage(@RequestParam("id") Long id, @RequestParam("images") MultipartFile multipartFile) throws IOException {
        return created(
                ImageResponse.of(
                        imageService.saveImage(id, multipartFile)
                )
        );
    }

    @GetMapping
    @ResponseStatus(OK)
    public ApiResponse findAllImage() {
        return ok(
                imageService.findAllImage()
                        .stream()
                        .map(ImageResponse::of)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public ApiResponse findOneImageById(@PathVariable Long id) {
        return ok(
                ImageResponse.of(
                        imageService.findImageById(id)
                )
        );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public ApiResponse deleteOneImageById(@PathVariable Long id) {
        imageService.deleteImageById(id);
        return noContent();
    }
}
