package com.todo.service;

import com.todo.domain.entities.Todo;
import com.todo.domain.entities.TodoStatus;
import com.todo.domain.entities.User;
import com.todo.domain.models.binding.TodoBindingModel;
import com.todo.domain.models.view.TodoViewModel;
import com.todo.repository.TodoRepository;
import com.todo.repository.TodoStatusRepository;
import com.todo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    private final TodoStatusRepository todoStatusRepository;

    private final UserRepository userRepository;

    public TodoService(TodoRepository todoRepository, TodoStatusRepository todoStatusRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.todoStatusRepository = todoStatusRepository;
        this.userRepository = userRepository;
    }

    public List<Todo> getTodos(Integer userId) {
        User user = this.userRepository.findById(userId).orElse(null);

        if (user == null) {
            return new ArrayList<>();
        }

        return this.todoRepository.findAllByUser(user);
    }

    public boolean deleteTodo(Integer todoId) {
        this.todoRepository.deleteById(todoId);

        return true;
    }

    public boolean updateTodo(Integer id, TodoBindingModel bindingModel) {
        TodoStatus todoStatus = this.todoStatusRepository.findById(bindingModel.getStatus()).orElse(null);

        User user = this.userRepository.findById(bindingModel.getUserId()).orElse(null);

        if (todoStatus == null || user == null) {
            return false;
        }

        Todo todo = this.todoRepository.findById(id).orElse(null);

        if (todo == null) {
            return false;
        }

        todo.setContent(bindingModel.getContent());
        todo.setStatus(todoStatus);
        todo.setUser(user);

        this.todoRepository.save(todo);

        return true;
    }

    public boolean addTodo(TodoBindingModel bindingModel) {
        TodoStatus todoStatus = this.todoStatusRepository.findById(bindingModel.getStatus()).orElse(null);

        User user = this.userRepository.findById(bindingModel.getUserId()).orElse(null);

        if (todoStatus == null || user == null) {
            return false;
        }

        Todo todo = Todo.builder()
                .content(bindingModel.getContent())
                .status(todoStatus)
                .user(user)
                .build();

        this.todoRepository.save(todo);

        return true;
    }

    public List<TodoViewModel> transformToViewModels(List<Todo> todos) {
        List<TodoViewModel> viewModels = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();

        for (Todo todo : todos) {
            TodoViewModel viewModel = modelMapper.map(todo, TodoViewModel.class);
            viewModel.setUsername(todo.getUser().getUsername());
            viewModel.setStatusName(todo.getStatus().getName());

            viewModels.add(viewModel);
        }

        return viewModels;
    }

    public boolean changeStatus(Integer todoId, TodoStatus status) {
        Todo todo = this.todoRepository.findById(todoId).orElse(null);

        if (todo == null) {
            return false;
        }

        todo.setStatus(status);
        this.todoRepository.save(todo);

        return true;
    }

    public TodoStatus getStatus(Integer statusId) {
        return this.todoStatusRepository.findById(statusId).orElse(null);
    }
}
