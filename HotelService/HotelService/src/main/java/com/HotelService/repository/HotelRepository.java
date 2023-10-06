package com.HotelService.repository;

import com.HotelService.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository  extends JpaRepository<Hotel, String> {
}
