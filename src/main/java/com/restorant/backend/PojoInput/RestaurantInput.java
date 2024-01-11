package com.restorant.backend.PojoInput;

import com.restorant.backend.POJO.Address;
import com.restorant.backend.POJO.RestaurantType;

public class RestaurantInput {

    private String name;
    private Address ville;
    private RestaurantType specialite;


    public RestaurantInput(String name, Address ville, RestaurantType specialite) {
        this.name = name;
        this.ville = ville;
        this.specialite = specialite;
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

}
