package com.backend.domain.location.dto;

import com.backend.domain.location.domain.entity.Location;
import com.backend.global.domain.code.service.CodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationMapper {

    private final CodeService codeService;

    public Location toEntity(LocationRequest request) {
        return Location.builder()
                .regionCode(codeService.findByMemo(request.getRegion()))
                .name(request.getName())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .build();
    }

    public LocationResponse fromEntity(Location location) {
        return LocationResponse.builder()
                .id(location.getId())
                .region(location.getRegionCode().getMemo())
                .name(location.getName())
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .build();
    }
}
