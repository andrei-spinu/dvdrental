package com.endava.dvdrental.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Integer id){
        super("Object with id: " + id + " doesn't exits!");
    }
}
