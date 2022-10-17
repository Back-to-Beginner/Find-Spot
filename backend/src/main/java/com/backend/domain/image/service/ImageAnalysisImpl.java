package com.backend.domain.image.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ImageAnalysisImpl implements
        ImageAnalysis<Boolean> {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${pythonServer.address}")
    private String imageAnalysisServer;

    @Value("${pythonServer.url}")
    private String url;

    @Override
    public Boolean analyseImage(
            String challengeImageUrl,
            String missionImageUrl
    ) {
        URI uri = UriComponentsBuilder
                .fromUriString(imageAnalysisServer)
                .path(url)
                .encode()
                .build()
                .toUri();

        ResponseEntity<String> result = restTemplate.postForEntity(
                uri,
                Map.of("mission", missionImageUrl, "trial", challengeImageUrl),
                String.class);

        return Objects.requireNonNull(result.getBody()).equals("True");
    }
}
