package com.example.kydroid.todolist.domain.common;

public class ResourceCreationConflictException extends Exception {

    public ResourceCreationConflictException(String resourceName) {
        super(String.format("Resource (%s) creation conflict !!!", resourceName));
    }
}
