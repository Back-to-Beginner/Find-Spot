package com.backend.domain.image.service;

public interface ImageAnalysis <T> {
    T analyseImage(String challengeImageUrl, String missionImageUrl);
}
