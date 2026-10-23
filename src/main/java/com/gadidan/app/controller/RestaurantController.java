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

    public int registerRestaurant(RestaurantDto restaurantDto) {
        int nameResult = repository.findRestaurantByName(restaurantDto);
        int result;

        if (nameResult > 0) {
            ConsoleView.displayMessage("레스토랑이 이미 존재하므로 리뷰만 등록합니다.");
            return 1;
        }

        result = repository.save(restaurantDto);

        if (result > 0) {
            ConsoleView.displayMessage("레스토랑 등록이 완료되었습니다.");
        } else {
            ConsoleView.displayMessage("레스토랑 등록에 실패하였습니다.");
        }

        return result;
    }
}
