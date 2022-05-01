package com.backend.domain.trip.dto;

import com.backend.domain.trip.domain.entity.Trip;
import com.backend.domain.trip.service.TripService;
import com.backend.domain.user.service.UserService;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.sql.Date;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class TripRequest {
    @NotNull
    private Long userId;

    @NotNull
    private String title;

    @NotNull
    private String review;

    //    @Past
    @NotNull
    private Date beginDate;

    //    @Past
    @NotNull
    private Date endDate;

    @NotNull
    private int fullCost;

    @Builder
    public Trip toEntity(UserService userService) {
        return Trip.builder()
                .user(userService.findUserById(this.userId))
                .title(this.title)
                .review(this.review)
                .begin_date(this.beginDate)
                .end_date(this.endDate)
                .fullCost(this.fullCost)
                .build();
    }
}
