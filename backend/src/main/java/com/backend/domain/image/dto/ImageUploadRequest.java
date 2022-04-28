package com.backend.domain.image.dto;

import com.backend.domain.image.domain.entity.Image;
import com.backend.domain.location.service.LocationService;
import com.backend.infra.aws.component.ImageUploader;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.io.IOException;

@RequiredArgsConstructor
public class ImageUploadRequest {

    private final ImageUploader imageUploader;
    private final LocationService locationService;

    @NotEmpty
    private Long location_id;

    public Image toEntity(MultipartFile multipartFile) throws IOException {
        return Image.builder()
                .path(imageUploader.upload(multipartFile))
                .location(locationService.findLocationById(this.location_id))
                .build();
    }
}
