package org.sparta.shop99.repository;

import org.sparta.shop99.entity.Item;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.PropertyResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class ShopRepository {

    private final JdbcTemplate jdbcTemplate;
    private final PropertyResolver propertyResolver;

    public ShopRepository(JdbcTemplate jdbcTemplate, @Qualifier("propertyResolver") PropertyResolver propertyResolver) {
        this.jdbcTemplate = jdbcTemplate;
        this.propertyResolver = propertyResolver;
    }

    public Item save(Item item) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO items (title, content, price, username) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update( con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, item.getTitle());
                    preparedStatement.setString(2, item.getContent());
                    preparedStatement.setInt(3, item.getPrice());
                    preparedStatement.setString(4, item.getUsername());
                    return preparedStatement;
                },
                keyHolder);

            int id = keyHolder.getKey().intValue();
            item.setId(id);
            return item;
    }
}
