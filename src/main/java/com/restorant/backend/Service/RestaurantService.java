package com.restorant.backend.Service;

import com.restorant.backend.POJO.Address;
import com.restorant.backend.POJO.Restaurant;
import com.restorant.backend.POJO.RestaurantNotFoundException;
import com.restorant.backend.POJO.RestaurantType;

import java.util.List;
import java.util.Optional;

public interface RestaurantService {

    Restaurant create(Restaurant restaurant);
    Optional<Restaurant> findById(Integer id);
    List<Restaurant> findAll();
    List<Restaurant> findByVille(Address ville);
    List<Restaurant> findBySpecialite(RestaurantType specialite);
    List<Restaurant> findByName(String name);

    void deleteRestaurant(Integer id);

    List<Restaurant> findBySpecialiteAndVille(RestaurantType specialite, Address ville);

    Restaurant update(Restaurant restaurantToUpdate);

}
