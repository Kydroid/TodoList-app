package com.example.kydroid.todolist.domain.common;

public class ResourceCreationFailedException extends Exception {

    public ResourceCreationFailedException(String resourceName) {
        super(String.format("Resource (%s) creation failed !!!", resourceName));
    }
}
