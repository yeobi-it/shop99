package org.sparta.shop99.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sparta.shop99.dto.ShopRequestDto;

@Entity
@Getter
@Setter
@Table(name="item")
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", nullable = false )
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "price", nullable = false )
    private int price;

    @Column(name = "username", nullable = false )
    private String username;

    public Item(ShopRequestDto shopRequestDto){
        this.id = shopRequestDto.getId();
        this.title = shopRequestDto.getTitle();
        this.content = shopRequestDto.getContent();
        this.price = shopRequestDto.getPrice();
        this.username = shopRequestDto.getUsername();
    }
}


