package com.restorant.backend.POJO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.beans.Transient;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Restaurant {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Address ville;

    @Enumerated(EnumType.STRING)
    @Column(name = "specialite")
    private RestaurantType specialite;

    private double rating;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.REMOVE)
    private List<Menu> menus;

    @Column(name = "address")
    private String address;

    @ElementCollection
    @CollectionTable(name = "restaurant_ratings", joinColumns = @JoinColumn(name = "restaurant_id"))
    @Column(name = "rating")
    @JsonIgnore
    private List<Integer> ratings;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    protected List<Review> reviews;





    public Restaurant() {
    }

    public Restaurant(Integer id, String name, Address ville, RestaurantType specialite) {
        this.id = id;
        this.name = name;
        this.ville = ville;
        this.specialite = specialite;
    }
    public Restaurant(String name, Address ville, RestaurantType specialite) {
        this.name = name;
        this.ville = ville;
        this.specialite = specialite;
    }


    /**
     * @return Integer return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Address return the ville
     */
    public Address getVille() {
        return ville;
    }

    /**
     * @param ville the ville to set
     */
    public void setVille(Address ville) {
        this.ville = ville;
    }

    /**
     * @return RestaurantType return the specialite
     */
    public RestaurantType getSpecialite() {
        return specialite;
    }

    /**
     * @param specialite the specialite to set
     */
    public void setSpecialite(RestaurantType specialite) {
        this.specialite = specialite;
    }

    /**
     * @return double return the rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * @return List<Menu> return the menus
     */
    public List<Menu> getMenus() {
        return menus;
    }

    /**
     * @param menus the menus to set
     */
    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    /**
     * @return String return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return List<Integer> return the ratings
     */
    public List<Integer> getRatings() {
        return ratings;
    }

    /**
     * @param ratings the ratings to set
     */
    public void setRatings(List<Integer> ratings) {
        this.ratings = ratings;
    }


}
