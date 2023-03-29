package com.todo.web.controllers;

import com.todo.common.exceptions.BadRequestException;
import com.todo.domain.models.binding.UserBindingModel;
import com.todo.domain.models.view.ResponseViewModel;
import com.todo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
}
