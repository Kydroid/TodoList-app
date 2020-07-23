package com.example.kydroid.todolist.domain.todolist;

import com.example.kydroid.todolist.domain.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TodoItem extends BaseEntity {

    @NotBlank(message = "Name is mandatory")
    private String name;
    private TodoItemStatus todoItemStatus;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    private TodoList todoList;
}
