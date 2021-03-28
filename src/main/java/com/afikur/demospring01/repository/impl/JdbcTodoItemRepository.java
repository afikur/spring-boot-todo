package com.afikur.demospring01.repository.impl;

import com.afikur.demospring01.model.TodoItem;
import com.afikur.demospring01.repository.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcTodoItemRepository implements TodoItemRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTodoItemRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<TodoItem> findAll() {
        String SELECT_ALL_TODO = "SELECT * FROM todo";

        return jdbcTemplate.query(SELECT_ALL_TODO, this::mapRowToTodoItem);
    }

    private TodoItem mapRowToTodoItem(ResultSet rs, int rowNum) throws SQLException {
        return new TodoItem(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getDate("createdAt")
        );
    }
}
