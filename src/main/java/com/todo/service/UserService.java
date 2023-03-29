package com.todo.service;

import com.todo.common.exceptions.BadRequestException;
import com.todo.domain.entities.User;
import com.todo.domain.models.binding.UserBindingModel;
import com.todo.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User cannot be found");
        }

        return user;
    }

    public void register(UserBindingModel bindingModel) {
        if (this.userRepository.existsByUsername(bindingModel.getUsername())) {
            throw new BadRequestException("Username already exists");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPass = passwordEncoder.encode(bindingModel.getPassword());

        User user = User.builder()
                .username(bindingModel.getUsername())
                .password(hashedPass)
                .build();

        this.userRepository.save(user);
    }
}
