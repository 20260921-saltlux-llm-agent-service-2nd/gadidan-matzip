package com.gadidan.app.repository;

import com.gadidan.app.model.dto.RestaurantDto;

public interface RestaurantRepository {

    /// 리뷰 등록
    /// @param restaurantDto ReviewDto 객체
    /// @return 영향 받은 행의 수
    int save(RestaurantDto restaurantDto);

    /// 레스토랑 중복 등록 여부 확인
    /// @param restaurantDto 확인할 레스토랑 이름
    /// @return 등록된 레스토랑 ID, 존재하지 않으면 0
    int findRestaurantByName(String restaurantDto);

}
