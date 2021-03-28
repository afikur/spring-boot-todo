package com.afikur.demospring01.repository;

import com.afikur.demospring01.model.TodoItem;

import java.util.List;

public interface TodoItemRepository {
    List<TodoItem> findAll();
    TodoItem save(TodoItem todoItem);
}
