package com.HotelService.service.impl;

import com.HotelService.entity.Hotel;
import com.HotelService.repository.HotelRepository;
import com.HotelService.service.HotelService;
import com.HotelService.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;


    @Override
    public Hotel createHotel(Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setId(hotelId);
        Hotel hotel1 = hotelRepository.save(hotel);
        return hotel1;
    }

    @Override
    public List<Hotel> getAllHotels() {
        List<Hotel> hotelList = hotelRepository.findAll();
        return hotelList;
    }

    @Override
    public Hotel getById(String id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel with given id not found check with anthoer id :" + id));
        return hotel;
    }

    @Override
    public Hotel updateHotel(String id, Hotel updateHotel) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel is Not there with :" + id));
        hotel.setName(updateHotel.getName());
        hotel.setLocation(updateHotel.getLocation());
        hotel.setAbout(updateHotel.getAbout());

        return hotelRepository.save(hotel);
    }

    @Override
    public void deleteHotel(String id) {
        if (hotelRepository.existsById(id)){
            hotelRepository.deleteById(id);
        }else {
            throw  new ResourceNotFoundException("Hotel wis not found with this id :"+ id);
        }

    }
}
