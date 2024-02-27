package com.customer.application.exception;

import java.io.IOException;

public class PDFException extends RuntimeException{
    public PDFException(String message, IOException e){
        super(message);
    }
}
