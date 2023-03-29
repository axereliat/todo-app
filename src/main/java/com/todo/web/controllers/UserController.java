package com.todo.web.controllers;

import com.todo.common.exceptions.BadRequestException;
import com.todo.domain.models.binding.UserBindingModel;
import com.todo.domain.models.view.ResponseViewModel;
import com.todo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<ResponseViewModel> register(@RequestBody @Valid UserBindingModel bindingModel,
                                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ResponseViewModel viewModel =
                    new ResponseViewModel(bindingResult.getAllErrors().get(0).getDefaultMessage());

            return ResponseEntity.badRequest().body(viewModel);
        }

        try {
            this.userService.register(bindingModel);

            return ResponseEntity.ok(new ResponseViewModel("User was successfully registered"));
        } catch (BadRequestException exception) {
            return ResponseEntity.badRequest().body(new ResponseViewModel(exception.getMessage()));
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody UserBindingModel bindingModel) {
        this.userService.updateInfo(id, bindingModel.getUsername());

        return ResponseEntity.ok().body(this.userService.findByUsername(bindingModel.getUsername()));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity destroy(@PathVariable Integer id) {
        this.userService.deleteUser(id);

        return ResponseEntity.ok().body(Map.of("message", "ok"));
    }

    @GetMapping("/users")
    public ResponseEntity show() {
        return ResponseEntity.ok().body(this.userService.getAll());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity details(@PathVariable Integer id) {
        return ResponseEntity.ok().body(this.userService.getById(id));
    }
}
