package com.todo.domain.models.binding;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
public class TodoBindingModel {

    @NotEmpty
    private String content;

    @Positive
    private Integer status;

    @Positive
    private Integer userId;
}
