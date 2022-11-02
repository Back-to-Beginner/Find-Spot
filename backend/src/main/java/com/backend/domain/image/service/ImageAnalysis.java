package com.backend.domain.image.service;

import com.backend.domain.image.domain.ImageSlice;

public interface ImageAnalysis <T> {
    T analyseImage(String challengeImageUrl, String missionImageUrl, ImageSlice slice);
}
