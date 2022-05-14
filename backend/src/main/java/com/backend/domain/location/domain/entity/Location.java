package com.backend.domain.location.domain.entity;

import javax.persistence.*;

import com.backend.global.domain.basetime.domain.entity.BaseTimeEntity;
import lombok.*;

@Getter
@RequiredArgsConstructor
@Entity // This tells Hibernate to make a table out of this class
public class Location extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String region;

	@Column(unique = true, nullable = false)
	private String name;

	private double latitude;
	private double longitude;

	@Builder
	public Location(String region, String name, int cost, double latitude, double longitude) {
		this.region =region;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
	}

}
