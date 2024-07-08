package org.sparta.shop99.service;

import org.sparta.shop99.dto.ShopRequestDto;
import org.sparta.shop99.dto.ShopResponseDto;
import org.sparta.shop99.entity.Item;
import org.sparta.shop99.repository.ShopRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<ShopResponseDto> getItem() {

        return shopRepository.findAll();

    }

    public int update(int id, ShopRequestDto shopRequestDto) {

        // 해당 메모가 DB에 존재하는지 확인
        Item item = shopRepository.findById(id);
        if (item != null) {
            // memo 내용 수정
            shopRepository.update(id, shopRequestDto);

            return id;
        } else {
            throw new IllegalArgumentException("선택한 상품은 존재하지 않습니다.");
        }
    }

    public int delete(int id) {

        // 해당 메모가 DB에 존재하는지 확인
        Item item = shopRepository.findById(id);
        if (item != null) {
            // memo 삭제
            shopRepository.delete(id);
            return id;
        } else {
            throw new IllegalArgumentException("선택한 상품은 존재하지 않습니다.");
        }
    }
}
