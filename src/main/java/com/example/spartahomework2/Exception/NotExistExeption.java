package com.example.spartahomework2.Exception;

public class NotExistExeption extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public NotExistExeption(String message) {
        super(message);
    }
}
