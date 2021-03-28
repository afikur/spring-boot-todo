package com.afikur.demospring01.controller;

import com.afikur.demospring01.model.TodoItem;
import com.afikur.demospring01.repository.TodoItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/todo")
public class TodoItemController {
    private static Logger log = LoggerFactory.getLogger(TodoItemController.class);

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

    @GetMapping("/add")
    public String getTodoForm(Model model) {
        model.addAttribute("todoItem", new TodoItem());
        return "todoForm";
    }

    @PostMapping("/add")
    public String saveTodoItem(@ModelAttribute("todoItem") TodoItem todoItem) {
        TodoItem saved = todoItemRepository.save(todoItem);
        log.info("saved successfully: {}", saved);
        return "redirect:/todo";
    }
}
