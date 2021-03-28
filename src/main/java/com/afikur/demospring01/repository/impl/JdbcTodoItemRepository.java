package com.afikur.demospring01.repository.impl;

import com.afikur.demospring01.model.TodoItem;
import com.afikur.demospring01.repository.TodoItemRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @Override
    public TodoItem save(TodoItem todoItem) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("todo")
                .usingGeneratedKeyColumns("id");

        ObjectMapper objectMapper = new ObjectMapper();

        @SuppressWarnings("unchecked")
        Map<String, Object> values = objectMapper.convertValue(todoItem, Map.class);

        Date now = new Date();
        values.put("createdAt", now);

        int id = simpleJdbcInsert
                .executeAndReturnKey(values)
                .intValue();

        todoItem.setId(id);
        todoItem.setCreatedAt(now);

        return todoItem;
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
