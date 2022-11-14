package com.backend.domain.image.service;

import java.util.List;

public interface ImageAnalysis <T> {
    T analyseImage(String challengeImageUrl, String missionImageUrl, List<Integer> slice);
}
