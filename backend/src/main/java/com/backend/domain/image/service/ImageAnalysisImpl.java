package com.backend.domain.image.service;

import com.backend.global.error.ErrorCode;
import com.backend.global.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

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
    public Boolean analyseImage(String challengeImageUrl, String missionImageUrl) {
        //TODO 이미지 url 전송 기능 구현, 플라스크 서버 s3 이미지로 분석 가능하도록 변경
        Optional<String> result = restTemplate.getForObject(imageAnalysisServer + url, Optional.class);
        return result.orElseThrow(() -> new NotFoundException(ErrorCode.BAD_REQUEST, "analyse result is not available"))
                .equals("True");
    }
}
