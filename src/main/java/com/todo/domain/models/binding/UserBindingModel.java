package com.todo.domain.models.binding;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserBindingModel {

    @NotBlank
    @NotEmpty
    @NotNull
    private String username;
}
