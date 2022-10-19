package com.backend.domain.image.service;

import com.backend.domain.image.dto.AnalysisRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class ImageAnalysisImpl implements
        ImageAnalysis<Boolean> {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${pythonServer.address}")
    private String imageAnalysisServer;

    @Value("${pythonServer.url}")
    private String url;

    private final List<Integer> appleSlice = List.of(130, 300, 130, 245);
    private final List<Integer> phaSlice = List.of(130, 230, 120, 300);

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
                AnalysisRequest.builder()
                        .mission(missionImageUrl)
                        .trial(challengeImageUrl)
                        .slice(appleSlice)
                        .build(),
                String.class);

        log.info(result.getBody());

        return Objects.requireNonNull(result.getBody()).equals("True");
    }
}
