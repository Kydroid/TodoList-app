package com.example.kydroid.todolist.domain.todolist;

import com.example.kydroid.todolist.domain.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TodoList extends BaseEntity {

    @NotBlank(message = "Name is mandatory")
    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "todoList", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TodoItem> todoItems;
}
