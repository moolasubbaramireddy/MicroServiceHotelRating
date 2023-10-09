package com.ratingService.service;

import com.ratingService.entity.Rating;

import java.util.List;

public interface RatingService {
//create
    Rating create(Rating rating);

    //get All ratings
    List<Rating> getRatings();

    //getAll ratings by user id
    List<Rating> getRatingByUserId(String userId);

    //getAll ratings by hotel id
    List<Rating> getRatingByHotelId(String hotelId);


}
