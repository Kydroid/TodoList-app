package com.example.kydroid.todolist.domain.common;

public class ResourceInvalidException extends Exception {
    public ResourceInvalidException(String resourceName, Integer resourceId) {
        super(String.format("Resource (%s with id=%s) invalid, please check your data request !!!", resourceName, resourceId));
    }
}
