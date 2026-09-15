package com.projeto.ecommerceapii.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String msg){
        super(msg);
    }
}
