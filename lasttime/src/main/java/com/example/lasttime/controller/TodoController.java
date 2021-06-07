package com.example.lasttime.controller;

import com.example.lasttime.model.Todo;
import com.example.lasttime.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TodoController {
    @Autowired
    private TodoService todoService;
    @GetMapping("/")
    public String viewHomePage(Model model)
    {
        return findPaginated(1, model);

    }
    @GetMapping("/showNewTodoForm")
    public String showNewTodoForm(Model model){
        Todo todo = new Todo();
        model.addAttribute("todo", todo);
        return "newTodo";
    }
    @PostMapping("/saveTodo")
    public String saveTodo( @ModelAttribute("todo") Todo todo){
        todoService.saveTodo(todo);
        return "redirect:/";
    }
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable( value = "id") Long id, Model model){
        Todo todo = todoService.getTodoByPerson(id);
        model.addAttribute("todo", todo);
        return "updateTodo";
    }
    @GetMapping("/deleteTodo/{id}")
    public String deleteTodo(@PathVariable (value = "id") Long id) {
        this.todoService.deleteTodoById(id);
        return "redirect:/";
    }
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo, Model model)
    {
        int pageSize = 5;
        Page<Todo> page = todoService.findPaginated(pageNo, pageSize);
        List<Todo> listPages = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listTodos", listPages);
        return "index";
    }
}
