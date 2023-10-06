package com.HotelService.service;

import com.HotelService.entity.Hotel;

import java.util.List;

public interface HotelService {
    //crete
    Hotel createHotel(Hotel hotel);

    //getAllHotels
    List<Hotel> getAllHotels();

    //getSingleHotel with id
    Hotel getById(String id);

    //update Hotel details
    Hotel updateHotel(String id, Hotel updateHotel);

    //delete Hotel by id
    void deleteHotel(String id);
}
