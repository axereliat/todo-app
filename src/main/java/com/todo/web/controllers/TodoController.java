package com.todo.web.controllers;

import com.todo.common.exceptions.BadRequestException;
import com.todo.domain.entities.Todo;
import com.todo.domain.entities.TodoStatus;
import com.todo.domain.entities.User;
import com.todo.domain.models.binding.StatusBindingModel;
import com.todo.domain.models.binding.TodoBindingModel;
import com.todo.domain.models.view.ResponseViewModel;
import com.todo.domain.models.view.TodoViewModel;
import com.todo.service.TodoService;
import com.todo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    private final UserService userService;

    public TodoController(TodoService todoService, UserService userService) {
        this.todoService = todoService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ResponseViewModel> store(@RequestBody @Valid TodoBindingModel bindingModel,
                                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ResponseViewModel viewModel =
                    new ResponseViewModel(bindingResult.getAllErrors().get(0).getDefaultMessage());

            return ResponseEntity.badRequest().body(viewModel);
        }

        try {
            this.todoService.addTodo(bindingModel);

            return ResponseEntity.ok(new ResponseViewModel("Todo was successfully added"));
        } catch (BadRequestException exception) {
            return ResponseEntity.badRequest().body(new ResponseViewModel(exception.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseViewModel> update(@PathVariable Integer id,
                                                    @RequestBody @Valid TodoBindingModel bindingModel,
                                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ResponseViewModel viewModel =
                    new ResponseViewModel(bindingResult.getAllErrors().get(0).getDefaultMessage());

            return ResponseEntity.badRequest().body(viewModel);
        }

        try {
            this.todoService.updateTodo(id, bindingModel);

            return ResponseEntity.ok(new ResponseViewModel("Todo was successfully updated"));
        } catch (BadRequestException exception) {
            return ResponseEntity.badRequest().body(new ResponseViewModel(exception.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseViewModel> destroy(@PathVariable Integer id) {
        try {
            this.todoService.deleteTodo(id);

            return ResponseEntity.ok(new ResponseViewModel("Todo was successfully deleted"));
        } catch (BadRequestException exception) {
            return ResponseEntity.badRequest().body(new ResponseViewModel(exception.getMessage()));
        }
    }

    @GetMapping
    public List<TodoViewModel> show(@RequestParam Integer userId) {
        List<Todo> todos = this.todoService.getTodos(userId);
        List<TodoViewModel> todoViewModels = this.todoService.transformToViewModels(todos);

        return todoViewModels;
    }

    @PostMapping("/{id}/status")
    public ResponseEntity changeStatus(@PathVariable Integer id, @RequestBody StatusBindingModel bindingModel) {
        TodoStatus todoStatus = this.todoService.getStatus(bindingModel.getStatusId());

        todoService.changeStatus(bindingModel.getTodoId(), todoStatus);

        return ResponseEntity.ok().body(Map.of("message", "Status was successfully changed"));
    }
}
