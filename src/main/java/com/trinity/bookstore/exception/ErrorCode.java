package com.trinity.bookstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;

@Getter
public enum ErrorCode {
    AUTHOR_NOT_EXISTED(1001, "Author not existed", HttpStatus.NOT_FOUND),
    BOOK_NOT_EXISTED(1002, "Book not existed", HttpStatus.NOT_FOUND),
    AUTHOR_EXISTED(1003, "Author is existed", HttpStatus.BAD_REQUEST),
    UNCATEGORIZED_EXCEPTION(1004, "Uncategoried exception", HttpStatus.BAD_REQUEST),
    INVALID_KEY(1005, "Invalid key", HttpStatus.BAD_REQUEST),
    TITLE_KEY(1006, "Title field is required", HttpStatus.BAD_REQUEST),
    FULL_NAME_KEY(1007, "Full name field is required", HttpStatus.BAD_REQUEST),
    DOB_KEY(1008, "Dob field is required, yyyy-MM-dd", HttpStatus.BAD_REQUEST),
    ADDRESS_KEY(1009, "Address field is required", HttpStatus.BAD_REQUEST),
    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private int code;
    private String message;
    private HttpStatusCode statusCode;
}
