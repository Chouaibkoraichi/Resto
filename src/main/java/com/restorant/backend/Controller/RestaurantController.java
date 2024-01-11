package com.restorant.backend.Controller;
import com.restorant.backend.POJO.*;
import com.restorant.backend.PojoInput.RestaurantInput;
import com.restorant.backend.Service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


import javax.xml.transform.OutputKeys;
import java.io.InvalidClassException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;


@Controller
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;



    @GetMapping("/locate")
    public String getlocation(Model model){

         
        model.addAttribute("restaurant", restaurantService.findAll());
        model.addAttribute("Address", Address.values());
        model.addAttribute("specialite", RestaurantType.values());
        model.addAttribute("select", "Selectionnez la ville");
        model.addAttribute("selectSp", "Selectionnez la specialité de votre choix");
        model.addAttribute("empty", "");

        return "/restaurant/locate";
    }

    //Creating a restaurant
    @PostMapping("/post")
    public ResponseEntity<Restaurant> create(@RequestBody RestaurantInput input) {

        String name = input.getName();
        Address ville = input.getVille();
        RestaurantType specialite = input.getSpecialite();       

        Restaurant restaurant = new Restaurant(name, ville, specialite);
        restaurantService.create(restaurant);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);

    }

    //Getting all the restaurants in the Db
    @GetMapping("/post")
    public ResponseEntity<List<Restaurant>> findAll() {
        List<Restaurant> restaurants = restaurantService.findAll();
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping("/post/one/{id}")
    public ResponseEntity<Restaurant> findOneById(@PathVariable Integer id) throws RestaurantNotFoundException{

        Restaurant restaurant = restaurantService.findById(id).orElseThrow(() -> new RestaurantNotFoundException(id));

        return new ResponseEntity<>(restaurant,HttpStatus.OK);
    }

    //Getting all the restaurant in a specified City
    @GetMapping("/city/{address}")
    public ResponseEntity<List<Restaurant>> findAllByAddress(@PathVariable String address) throws InvalidCityArgumentException{

        if (!Arrays.stream(Address.values()).anyMatch(c -> c.name().equalsIgnoreCase(address))) {
            throw new InvalidCityArgumentException("Invalid city: " + address);
        }

        Address addressEnum = Address.valueOf(address.toUpperCase());
        List<Restaurant> restaurants = restaurantService.findByVille(addressEnum);

        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    //Getting all restaurants of the same restuarnt type
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Restaurant>> findAllByType(@PathVariable String type) throws InvalidRestaurantTypeException{

        if (!Arrays.stream(RestaurantType.values()).anyMatch(c -> c.name().equalsIgnoreCase(type))) {
            throw new InvalidRestaurantTypeException("Invalid Restaurant Type: " + type);
        }

        RestaurantType restaurantType = RestaurantType.valueOf(type.toUpperCase());
        List<Restaurant> restaurants = restaurantService.findBySpecialite(restaurantType);

        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    //Getting restuarants by their name
    @GetMapping("/post/{name}")
    public ResponseEntity<List<Restaurant>> findAllByName(@PathVariable String name) {
        List<Restaurant> restaurants = restaurantService.findByName(name);

        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }


    //Deleting a restaurant by their id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Integer id) throws RestaurantNotFoundException{
        restaurantService.findById(id).orElseThrow(()-> new RestaurantNotFoundException(id));
        restaurantService.deleteRestaurant(id);

        return ResponseEntity.noContent().build();
    }

    //Updating an existing restaurant
    // @PutMapping("/update/{id}")
    // public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Integer id, @RequestBody RestaurantInput input)
    //         throws RestaurantNotFoundException{
    //     Restaurant restaurant = restaurantService.findById(id).orElseThrow(() -> new RestaurantNotFoundException(id));

    //     restaurant.setName(input.getName());
    //     restaurant.setVille(input.getVille());
    //     restaurant.setSpecialite(input.getSpecialite());

    //     Restaurant updated = restaurantService.update(restaurant);

    //     return new ResponseEntity<>(updated, HttpStatus.OK);
    // }
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model)  throws RestaurantNotFoundException{
        Restaurant restaurant = restaurantService.findById(id).orElseThrow(() -> new RestaurantNotFoundException(id));
        model.addAttribute("restaurant", restaurant);
        return "locate"; // Adjust the template name accordingly
    }


    // //Getting restaurants in one city and sorting them by price
    // // @GetMapping("/cityprice/{address}/{sorter}")
    // // public ResponseEntity<List<Restaurant>> sortByPriceOnCity(@PathVariable String address, @PathVariable String sorter)
    // //         throws InvalidCityArgumentException {

    // //     if (!Arrays.stream(Address.values()).anyMatch(c -> c.name().equalsIgnoreCase(address))) {
    // //         throw new InvalidCityArgumentException("Invalid city: " + address);
    // //     }

    // //     Address addressEnum = Address.valueOf(address.toUpperCase());
    // //     List<Restaurant> restaurant = restaurantService.findByAddress(addressEnum);

    // //     if (restaurant.isEmpty())
    // //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    // //     if (sorter.equals("asc"))
    // //         restaurant.sort(Comparator.comparing(Restaurant::getAveragePrice));
    // //     else
    // //         restaurant.sort(Comparator.comparing(Restaurant::getAveragePrice).reversed());

    // //     return new ResponseEntity<>(restaurant, HttpStatus.OK);
    // // }

    // //Getting restaurants on a city and sorting them by rating
    // @GetMapping("/cityrating/{address}/{sorter}")
    // public ResponseEntity<List<Restaurant>> sortByRatingOnCity(@PathVariable String address, @PathVariable String sorter)
    //                                         throws InvalidCityArgumentException{

    //     if (!Arrays.stream(Address.values()).anyMatch(c -> c.name().equalsIgnoreCase(address))) {
    //         throw new InvalidCityArgumentException("Invalid city: " + address);
    //     }

    //     Address addressEnum = Address.valueOf(address.toUpperCase());
    //     List<Restaurant> restaurant = restaurantService.findByAddress(addressEnum);

    //     if (restaurant.isEmpty())
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    //     if (sorter.contains("asc"))
    //         restaurant.sort(Comparator.comparing(Restaurant::getRating));
    //     else
    //         restaurant.sort(Comparator.comparing(Restaurant::getRating).reversed());

    //     return new ResponseEntity<>(restaurant, HttpStatus.OK);
    // }

    // @GetMapping("/deliveries/{address}")
    // public ResponseEntity<List<Restaurant>> findByAddressAndDelivery(@PathVariable String address, @RequestParam boolean doDelivery)
    //                                         throws InvalidCityArgumentException{

    //     if (!Arrays.stream(Address.values()).anyMatch(c -> c.name().equalsIgnoreCase(address))) {
    //         throw new InvalidCityArgumentException("Invalid city: " + address);
    //     }

    //     Address address1 = Address.valueOf(address.toUpperCase());
    //     List<Restaurant> deliveries = restaurantService.findByCityAndDoDelivery(address1,doDelivery);

    //     return new ResponseEntity<>(deliveries,HttpStatus.OK);
    // }


    // @GetMapping("/nearme")
    // public ResponseEntity<List<Restaurant>> findAllRestaurantsNearBy(@RequestParam double longitude,
    //                                                                  @RequestParam double latitude){

    //     List<Restaurant> restaurants = restaurantService.findAll();

    //     for (Restaurant restaurant: restaurants){
    //         restaurant.setDistance(Restaurant.distance(restaurant.getLatitude(), restaurant.getLongitude(), latitude, longitude));
    //     }
    //     restaurants.sort(Comparator.comparingDouble(Restaurant::getDistance));

    //     return new ResponseEntity(restaurants, HttpStatus.OK);

    // }


@GetMapping("/find")
public ResponseEntity<List<Restaurant>> findRestaurants(
        @RequestParam(required = false) RestaurantType specialite,
        @RequestParam(required = false) Address ville) {

    List<Restaurant> filteredRestaurants;

    // Perform filtering based on parameters
    if (specialite != null && ville != null) {
        filteredRestaurants = restaurantService.findBySpecialiteAndVille(specialite, ville);
    } else if (specialite != null) {
        filteredRestaurants = restaurantService.findBySpecialite(specialite);
    } else if (ville != null) {
        filteredRestaurants = restaurantService.findByVille(ville);
    } else {
        // No filters applied, return all restaurants
        filteredRestaurants = restaurantService.findAll();
    }

    return new ResponseEntity<>(filteredRestaurants, HttpStatus.OK);
}


}
