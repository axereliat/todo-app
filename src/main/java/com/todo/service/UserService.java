package com.todo.service;

import com.todo.common.exceptions.BadRequestException;
import com.todo.domain.entities.User;
import com.todo.domain.models.binding.UserBindingModel;
import com.todo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    public void updateInfo(Integer id, String username) {
        User user = this.userRepository.findById(id).orElse(null);

        user.setUsername(username);
        this.userRepository.save(user);
    }

    public void deleteUser(Integer id) {
        this.userRepository.deleteById(id);
    }

    public User getById(Integer id) {
        return this.userRepository.findById(id).orElse(null);
    }

    public List<User> getAll() {
        return this.userRepository.findAll();
    }
}
