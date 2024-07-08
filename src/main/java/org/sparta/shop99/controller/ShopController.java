package org.sparta.shop99.controller;

import org.sparta.shop99.dto.ShopRequestDto;
import org.sparta.shop99.dto.ShopResponseDto;
import org.sparta.shop99.service.ShopService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop")
public class ShopController {

    private final ShopService shopService;

     public ShopController(ShopService shopService){
         this.shopService = shopService;
     }

     @PostMapping("shop")
    public ShopResponseDto createShop(@RequestBody ShopRequestDto requestDto){
         return shopService.createShop(requestDto);
     }
}
