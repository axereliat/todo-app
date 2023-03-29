package com.todo.service;

import com.todo.common.exceptions.BadRequestException;
import com.todo.domain.entities.User;
import com.todo.domain.models.binding.UserBindingModel;
import com.todo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(UserBindingModel bindingModel) {
        if (this.userRepository.existsByUsername(bindingModel.getUsername())) {
            throw new BadRequestException("Username already exists");
        }

        User user = User.builder()
                .username(bindingModel.getUsername())
                .build();

        this.userRepository.save(user);
    }
}
