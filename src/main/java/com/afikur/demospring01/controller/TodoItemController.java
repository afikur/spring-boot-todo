package com.afikur.demospring01.controller;

import com.afikur.demospring01.model.TodoItem;
import com.afikur.demospring01.repository.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/todo")
public class TodoItemController {
    private final TodoItemRepository todoItemRepository;

    @Autowired
    public TodoItemController(TodoItemRepository todoItemRepository) {
        this.todoItemRepository = todoItemRepository;
    }

    @GetMapping
    public String todoItems(Model model) {
        List<TodoItem> todoItems = todoItemRepository.findAll();

        model.addAttribute("todoItems", todoItems);

        return "todo";
    }
}
