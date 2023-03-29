package com.todo.repository;

import com.todo.domain.entities.Todo;
import com.todo.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {

    List<Todo> findAllByUser(User userId);
}
