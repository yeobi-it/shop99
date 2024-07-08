package org.sparta.shop99.repository;

import org.sparta.shop99.dto.ShopRequestDto;
import org.sparta.shop99.dto.ShopResponseDto;
import org.sparta.shop99.entity.Item;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.PropertyResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class ShopRepository {

    private final JdbcTemplate jdbcTemplate;


    public ShopRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }

    public Item save(Item item) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO item (title, content, price, username) VALUES (?, ?, ?, ?)";
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

    public List<ShopResponseDto> findAll() {
        String sql = "SELECT * FROM item";

        return jdbcTemplate.query(sql, new RowMapper<ShopResponseDto>() {
            @Override
            public ShopResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                // SQL 의 결과로 받아온 Memo 데이터들을 MemoResponseDto 타입으로 변환해줄 메서드
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                int price = rs.getInt("price");
                String username = rs.getString("username");
                return new ShopResponseDto(id, title, content, price, username);
            }
        });
    }

    public void update(int id, ShopRequestDto shopRequestDto) {
        String sql =
                "UPDATE item SET title = ?, content = ?, price = ?, username = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                shopRequestDto.getTitle(), shopRequestDto.getContent(),
                shopRequestDto.getPrice(), shopRequestDto.getUsername(), id);
    }

    public void delete(int id) {
        String sql = "DELETE FROM item WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public Item findById(int id) {
        // DB 조회
        String sql = "SELECT * FROM item WHERE id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if(resultSet.next()) {
                Item item = new Item();
                item.setTitle(resultSet.getString("title"));
                item.setContent(resultSet.getString("content"));
                item.setPrice(resultSet.getInt("price"));
                item.setUsername(resultSet.getString("username"));
                return item;
            } else {
                return null;
            }
        }, id);
    }
}
