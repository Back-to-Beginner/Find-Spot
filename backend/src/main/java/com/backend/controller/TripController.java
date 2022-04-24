package com.backend.controller;

import com.backend.entity.Image;
import com.backend.entity.Location;
import com.backend.entity.Trip;
import com.backend.service.ImageService;
import com.backend.service.LocationService;
import com.backend.service.TripService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class TripController {
    private final TripService tripService;
    private final LocationService locationService;
    private final ImageService imageService;

    public TripController(TripService tripService, LocationService locationService, ImageService imageService) {
        this.tripService = tripService;
        this.locationService = locationService;
        this.imageService = imageService;
    }

    //TODO: 추후에 데이터 입력 정해지면 엔티티 생성자 만들어 주기
    //TODO: 파라미터 다 받게 수정
    //TODO: trip 엔티티에 user 엔티티 FK로 받게 수정
    @PostMapping("/trips")
    public String newPost(@RequestParam("images")MultipartFile multipartFile) throws IOException {
        Trip trip = new Trip();
        trip.setText("test text");
        trip.setTitle("test title");
        tripService.save(trip);

        Location location = new Location();
        location.setTrip(trip);
        location.setName("test name");
        locationService.save(location);

        Image image = new Image();
        image.setLocation(location);
        image.setPath(imageService.upload(multipartFile));
        imageService.save(image);
        return trip.toString();
    }

    @GetMapping("/all")
    public String getAll() {
        String tempString = "";
        //TODO: 아직 모든 값을 채워주는 생성자가 없어서 null 떄문에 작동하지 않음 -> 생성자 필요
//        for (Trip trip : tripService.findAllTrip()){
//            tempString += trip.toString();
//            for (Location location : locationService.findByTripId(trip.getId())) {
//                tempString += "\n\t" + location.toString();
//                for (Image image : imageService.getImagesByLocationId(location.getId())) {
//                    tempString += "\n\t\t" + image.toString();
//                }
//            }
//        }

        return tempString;
    }
}
