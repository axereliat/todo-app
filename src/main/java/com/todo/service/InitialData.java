package com.todo.service;

import com.todo.domain.entities.TodoStatus;
import com.todo.repository.TodoStatusRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class InitialData {

    private static final String TODO_STATUS = "To do";
    private static final String DONE_STATUS = "Done";

    private final TodoStatusRepository todoStatusRepository;

    public InitialData(TodoStatusRepository todoStatusRepository) {
        this.todoStatusRepository = todoStatusRepository;
    }

    @PostConstruct
    public void seedData() {
        if (!this.todoStatusRepository.findAll().isEmpty()) {
            return;
        }

        TodoStatus todoStatusOne = new TodoStatus();
        todoStatusOne.setName(TODO_STATUS);
        TodoStatus todoStatusTwo = new TodoStatus();
        todoStatusTwo.setName(DONE_STATUS);

        this.todoStatusRepository.save(todoStatusOne);
        this.todoStatusRepository.save(todoStatusTwo);
    }
}
