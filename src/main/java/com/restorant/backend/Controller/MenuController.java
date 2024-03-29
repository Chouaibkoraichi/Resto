package com.restorant.backend.Controller;

import com.restorant.backend.POJO.Menu;
import com.restorant.backend.POJO.Restaurant;
import com.restorant.backend.POJO.RestaurantNotFoundException;
import com.restorant.backend.PojoInput.MenuInput;
import com.restorant.backend.Service.MenuService;
import com.restorant.backend.Service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    MenuService menuService;

    @Autowired
    RestaurantService restaurantService;


    @PostMapping("/add/{Rid}")
    public ResponseEntity<Menu> create(@PathVariable Integer Rid, @RequestBody MenuInput input)
                                throws RestaurantNotFoundException {

        Restaurant restaurant = restaurantService.findById(Rid).orElseThrow(() -> new RestaurantNotFoundException(Rid));

        String name = input.getName();
        double price = input.getPrice();
        String description = input.getDescription();

        Menu menu = new Menu(name, price, description, restaurant);
        menuService.create(menu);


        return new ResponseEntity<>(menu, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{rid}/{mid}")
    public ResponseEntity<Void> deleteItem(@PathVariable Integer rid, @PathVariable Integer mid)
                                throws RestaurantNotFoundException{

        Restaurant restaurant = restaurantService.findById(rid).orElseThrow(() -> new RestaurantNotFoundException(rid));
        List<Menu> menus = restaurant.getMenus();

        for (Menu menu : menus) {
            if (menu.getId().equals(mid)) {
                menuService.deleteItem(menu);
            }
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{Rid}/{id}")
    public ResponseEntity<Menu> updateMenu(@PathVariable Integer id, @PathVariable Integer Rid,
                                           @RequestBody MenuInput input) throws RestaurantNotFoundException{

        Restaurant restaurant = restaurantService.findById(Rid).orElseThrow(() -> new RestaurantNotFoundException(Rid));

        // for (Menu menu : menus) {
        //     if (menu.getId().equals(id)) {
        //         menu.setName(input.getName());
        //         menu.setPrice(input.getPrice());
        //         menu.setDescription(input.getDescription());

        //         menuService.update(menu);
        //     }

        //     return new ResponseEntity<>(menu, HttpStatus.OK);
        // }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
