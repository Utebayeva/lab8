package com.example.bookShop.exceptions;

public class IncorrectNumberException extends ArithmeticException{
    public IncorrectNumberException() {
        super("Incorrect number");
    }
    public IncorrectNumberException(String s) {
        super(s);
    }
}