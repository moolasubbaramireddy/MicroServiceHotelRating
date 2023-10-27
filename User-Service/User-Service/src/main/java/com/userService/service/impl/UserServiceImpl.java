package com.userService.service.impl;

import com.userService.entity.Hotel;
import com.userService.entity.Rating;
import com.userService.entity.User;
import com.userService.exception.ResourceNotFoundException;
import com.userService.externalService.HotelService;
import com.userService.repository.UserRepository;
import com.userService.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public User saveUser(User user) {
        //for ganarating random userID
        String s = UUID.randomUUID().toString();
        user.setUserId(s);
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        List<User> users = userRepository.findAll();

        for (User user : users) {
            Rating[] ratingOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);
            List<Rating> ratings = Arrays.asList(ratingOfUser);

            List<Rating> ratingList = ratings.stream().map(rating -> {
                Hotel hotel = hotelService.getHotel(rating.getHotelId());
                rating.setHotel(hotel);
                return rating;
            }).collect(Collectors.toList());

            user.setRatings(ratingList);
        }

        return users;
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User is not found with :" + userId));
        Rating[] ratingOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
       logger.info("{}",ratingOfUser);

       List<Rating> ratings= Arrays.stream(ratingOfUser).toList();


        List<Rating> ratingList = ratings.stream().map(rating -> {

            //api call to hotel service to get hotel
            //http://localhost:8082/hotels/375546ae-cf11-48e7-8cbc-0837506d7354
           // ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+ rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            //logger.info("response status code: {}",forEntity.getStatusCode());

            //set the hotel to rating
            rating.setHotel(hotel);
            //retrn the rating
            return  rating;

        }).collect(Collectors.toList());
        user.setRatings(ratingList);
        return user;
    }

    @Override
    public User updateUser(String userId, User updateUser) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User id is not avaliable check it once your database"));
        user.setName(updateUser.getName());
        user.setEmail(updateUser.getEmail());
        user.setAbout(updateUser.getAbout());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String userId) {
    if (userRepository.existsById(userId)){
        userRepository.deleteById(userId);
    }else {
        throw new ResourceNotFoundException("User not found with ID: " + userId);
    }
    }
}
