package com.example.lasttime.service;

import com.example.lasttime.model.Todo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TodoService {
    List<Todo> getAllTodos();
    void saveTodo(Todo todo);
    Todo getTodoByPerson(Long id);
    void deleteTodoById(Long id);
    Page<Todo> findPaginated(int pageNo, int pageSize);
}
