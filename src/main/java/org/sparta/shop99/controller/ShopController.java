package org.sparta.shop99.controller;

import org.sparta.shop99.dto.ShopRequestDto;
import org.sparta.shop99.dto.ShopResponseDto;
import org.sparta.shop99.service.ShopService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ShopController {

    private final ShopService shopService;

     public ShopController(ShopService shopService){
         this.shopService = shopService;
     }

     @PostMapping("/shop")
    public ShopResponseDto createShop(@RequestBody ShopRequestDto requestDto){
         return shopService.createShop(requestDto);
     }

    @GetMapping("/shop")
    public List<ShopResponseDto> getMemos() {

        return shopService.getItem();


    }

    @PutMapping("/shop/{id}")
    public int updateMemo(@PathVariable int id, @RequestBody ShopRequestDto requestDto) {

        return shopService.update(id ,requestDto);

    }

    @DeleteMapping("/shop/{id}")
    public int deleteMemo(@PathVariable int id) {

        return shopService.delete(id);

    }
}
