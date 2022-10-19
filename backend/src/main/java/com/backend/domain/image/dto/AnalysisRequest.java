package com.backend.domain.image.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AnalysisRequest {
    private String mission;
    private String trial;
    private List<Integer> slice;
}
