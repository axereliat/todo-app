package com.todo.service;

import com.todo.common.exceptions.BadRequestException;
import com.todo.domain.entities.User;
import com.todo.domain.models.binding.UserBindingModel;
import com.todo.domain.models.view.UserViewModel;
import com.todo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserViewModel register(UserBindingModel bindingModel) {
        User user = this.userRepository.findByUsername(bindingModel.getUsername());

        if (user != null) {
            return new UserViewModel(user.getId(), user.getUsername());
        }

        User newUser = User.builder()
                .username(bindingModel.getUsername())
                .build();

        this.userRepository.save(newUser);

        return new UserViewModel(newUser.getId(), newUser.getUsername());
    }

    public UserViewModel login(UserBindingModel bindingModel) {
        User user = this.userRepository.findByUsername(bindingModel.getUsername());

        if (user != null) {
            return new UserViewModel(user.getId(), user.getUsername());
        }
        throw new BadRequestException("User could not be found");
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
