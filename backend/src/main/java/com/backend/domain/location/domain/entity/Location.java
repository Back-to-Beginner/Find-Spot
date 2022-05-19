package com.backend.domain.location.domain.entity;

import javax.persistence.*;

import com.backend.global.domain.basetime.domain.entity.BaseTimeEntity;
import com.backend.global.domain.code.domain.entity.Code;
import lombok.*;

@Getter
@RequiredArgsConstructor
@Entity // This tells Hibernate to make a table out of this class
public class Location extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "code_id")
    private Code regionCode;

    @Column(nullable = false)
    private String name;

    private double latitude;
    private double longitude;

    @Builder
    public Location(Code regionCode, String name, double latitude, double longitude) {
        this.regionCode = regionCode;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
