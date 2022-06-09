package com.backend.domain.trip.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
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

    @Past
    @NotNull
    @JsonProperty("begin_date")
    private LocalDate beginDate;

    @Past
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
