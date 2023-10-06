package com.HotelService.controller;

import com.HotelService.entity.Hotel;
import com.HotelService.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
       Hotel hotel1= hotelService.createHotel(hotel);
        return  ResponseEntity.status(HttpStatus.CREATED).body(hotel1);
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels(){
        List<Hotel> allHotels = hotelService.getAllHotels();
        return ResponseEntity.status(HttpStatus.OK).body(allHotels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHostel(@PathVariable String id){
        Hotel hotel = hotelService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(hotel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hotel> updateHotels(@PathVariable String id,@RequestBody Hotel updateHotel){
        Hotel hotel1 = hotelService.updateHotel(id, updateHotel);
        return  ResponseEntity.status(HttpStatus.OK).body(hotel1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHotel(@PathVariable String id){
     hotelService.deleteHotel(id);
     return  ResponseEntity.noContent().build();
    }
}
