package com.afikur.demospring01.repository;

import com.afikur.demospring01.model.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoItemRepository extends JpaRepository<TodoItem, Integer> {
}
