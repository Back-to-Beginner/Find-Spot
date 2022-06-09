package com.backend.domain.trip.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class TripRequestDto {

    @NotNull
    @JsonProperty("user_id")
    private Long userId;

    @NotNull
    private String title;

    @NotNull
    private String region;

    @NotNull
    @JsonProperty("begin_date")
    private LocalDate beginDate;

    @NotNull
    @JsonProperty("end_date")
    private LocalDate endDate;

    @NotNull
    @JsonProperty("full_cost")
    private int fullCost;

    @NotNull
    @JsonProperty("tag_name_list")
    private List<String> tagNameList;

}
