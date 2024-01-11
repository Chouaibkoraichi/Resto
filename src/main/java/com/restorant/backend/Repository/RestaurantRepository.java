package com.restorant.backend.Repository;

import com.restorant.backend.POJO.Address;
import com.restorant.backend.POJO.Restaurant;
import com.restorant.backend.POJO.RestaurantType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant,Integer> {

    @Query("SELECT r from Restaurant r where r.ville = :ville")
    List<Restaurant> findByVille(@Param("ville") Address ville);

    @Query("SELECT r from Restaurant r where r.specialite = :specialite")
    List<Restaurant> findBySpecialite(@Param("specialite") RestaurantType specialite);

    @Query("SELECT r FROM Restaurant r WHERE r.name LIKE %:name%")
    List<Restaurant> findByName(@Param("name") String name);

    @Query("SELECT r FROM Restaurant r WHERE r.ville = :ville and r.specialite = :specialite")
    List<Restaurant> findBySpecialiteAndVille(@Param("specialite") RestaurantType specialite, @Param("ville") Address ville);


    @Query("SELECT r from Restaurant r")
    List<Restaurant> findAll();

}
