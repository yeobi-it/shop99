package org.sparta.shop99.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(name = "content")
    private String content;

    @Column(name = "price", nullable = false )
    private int price;

    @Column(name = "username", nullable = false )
    private String username;
}


