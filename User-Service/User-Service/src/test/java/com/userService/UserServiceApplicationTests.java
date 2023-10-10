package com.userService;

import com.userService.entity.Rating;
import com.userService.externalService.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
	private RatingService ratingService;

//	@Test
//	void  createRating(){
//		Rating rating=Rating.builder().rating(10).userId("").hotelId("").feedback("this recored creted in using feign clicent").build();
//		Rating rating1 = ratingService.createRating(rating);
//		System.out.println(rating1);
//	}


}
