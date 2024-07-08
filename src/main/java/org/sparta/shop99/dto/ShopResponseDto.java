package org.sparta.shop99.dto;

import lombok.Getter;
import org.sparta.shop99.entity.Item;

@Getter
public class ShopResponseDto {

    private int id;
    private String title;
    private String content;
    private int price;
    private String username;

    public ShopResponseDto(Item item) {
        this.id = item.getId();
        this.title = item.getTitle();
        this.content = item.getContent();
        this.price = item.getPrice();
        this.username = item.getUsername();

    }

    public ShopResponseDto(int id, String title, String content, int price, String username) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.price = price;
        this.username = username;
    }
}
