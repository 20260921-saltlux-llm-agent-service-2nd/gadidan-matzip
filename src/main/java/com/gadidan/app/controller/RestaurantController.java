package com.gadidan.app.controller;

import com.gadidan.app.dto.RestaurantDto;
import com.gadidan.app.repository.RestaurantRepository;
import com.gadidan.app.repository.impl.RestaurantRepositoryImpl;
import com.gadidan.app.view.ConsoleView;

public class RestaurantController {
    private RestaurantRepository repository;

    public RestaurantController() {
        repository = new RestaurantRepositoryImpl();
    }

    public int checkRestaurantExists(String restaurantName) {
        int result = repository.findRestaurantByName(restaurantName);

        if (result > 0) {
            int restaurantId = repository.findRestaurantByName(restaurantName);
            ConsoleView.displayMessage("식당이 이미 존재하므로 리뷰만 등록합니다.");
        }

        return result;
    }

    public int registerRestaurant(RestaurantDto restaurantDto) {
        int result = repository.save(restaurantDto);

        if (result > 0) {
            int restaurantId = repository.findRestaurantByName(restaurantDto.getRestaurantName());
            restaurantDto.setRestaurantId(restaurantId);
            if (restaurantId > 0) {
                ConsoleView.displayMessage("식당 등록이 완료되었습니다.");
            }
        } else {
            ConsoleView.displayMessage("식당 등록에 실패하였습니다.");
        }

        return result;
    }
}
