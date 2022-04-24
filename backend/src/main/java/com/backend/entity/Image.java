package com.backend.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Entity // This tells Hibernate to make a table out of this class
public class Image extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "location_id")
	private Location location;

	@Column(length = 300)
	private String path;

	@Override
	public String toString() {
		return "Image{" +
				"id=" + id +
				", location=" + location +
				", path='" + path + '\'' +
				'}';
	}
}
