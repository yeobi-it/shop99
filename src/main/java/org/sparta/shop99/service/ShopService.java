package org.sparta.shop99.service;

import org.sparta.shop99.dto.ShopRequestDto;
import org.sparta.shop99.dto.ShopResponseDto;
import org.sparta.shop99.entity.Item;
import org.sparta.shop99.repository.ShopRepository;
import org.springframework.stereotype.Service;

@Service
public class ShopService {

    private final ShopRepository shopRepository;

    public ShopService(ShopRepository shopRepository){
        this.shopRepository = shopRepository;
    }

    public ShopResponseDto createShop(ShopRequestDto shopRequestDto){
        Item item = new Item(shopRequestDto);

        Item saveItem = shopRepository.save(item);
        ShopResponseDto shopResponseDto = new ShopResponseDto(item);
        return shopResponseDto;
    }
}
