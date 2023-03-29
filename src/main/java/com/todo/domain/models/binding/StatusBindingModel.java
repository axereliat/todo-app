package com.todo.domain.models.binding;

import lombok.Data;

import javax.validation.constraints.Positive;

@Data
public class StatusBindingModel {

    @Positive
    private Integer statusId;

    @Positive
    private Integer todoId;
}
