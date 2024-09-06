package com.app.blog.model.exception;

public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException() {
        super("You are not authorized to access this resource");
    }
}
