package com.backend.domain.location.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.backend.domain.trip.domain.entity.Trip;
import com.backend.global.BaseTimeEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Entity // This tells Hibernate to make a table out of this class
public class Location extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "trip_id")
	private Trip trip;

	private Region region;

	private String name;

	private String review;

	private int cost;

	private double latitude;
	private double longitude;

	@Override
	public String toString() {
		return "Location{" +
				"id=" + id +
				", trip=" + trip +
				", region=" + region +
				", name='" + name + '\'' +
				", cost=" + cost +
				", latitude=" + latitude +
				", longitude=" + longitude +
				'}';
	}
}
