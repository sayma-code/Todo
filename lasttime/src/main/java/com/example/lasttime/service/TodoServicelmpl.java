package com.example.lasttime.service;

import com.example.lasttime.model.Todo;
import com.example.lasttime.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.management.RuntimeErrorException;
import java.util.List;
import java.util.Optional;

@Service
public class TodoServicelmpl implements TodoService{
    @Autowired
    private TodoRepository todoRepository;
    @Override
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    @Override
    public void saveTodo(Todo todo) {
        this.todoRepository.save(todo);
    }

    @Override
    public Todo getTodoByPerson(Long id) {
        Optional<Todo> optional = todoRepository.findById(id);
        Todo todo = null;
        if(optional.isPresent()){
            todo = optional.get();
        }else {
            throw new RuntimeException("Todo not found for id : "+ id);
        }
        return todo;
    }

    @Override
    public void deleteTodoById(Long id) {
        this.todoRepository.deleteById(id);
    }

    @Override
    public Page<Todo> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);
        return this.todoRepository.findAll(pageable);
    }


}
