package com.todo.domain.models.view;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
public class TodoViewModel {

    private String content;

    private String statusName;

    private String username;
}
