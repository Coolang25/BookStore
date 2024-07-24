package com.trinity.bookstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;

@Getter
public enum ErrorCode {
    AUTHOR_NOT_EXISTED(1001, "Author not existed", HttpStatus.NOT_FOUND),
    BOOK_NOT_EXISTED(1002, "Book not existed", HttpStatus.NOT_FOUND),
    AUTHOR_EXISTED(1003, "Author is existed", HttpStatus.BAD_REQUEST),
    BOOK_EXISTED(1004, "Book is existed", HttpStatus.BAD_REQUEST),
    UNCATEGORIZED_EXCEPTION(1005, "Uncategoried exception", HttpStatus.BAD_REQUEST),
    INVALID_KEY(1006, "Invalid key", HttpStatus.BAD_REQUEST),
    TITLE_KEY(1007, "Title field is required", HttpStatus.BAD_REQUEST),
    FULL_NAME_KEY(1008, "Full name field is required", HttpStatus.BAD_REQUEST),
    DATE_KEY(1009, "Field is required, yyyy-MM-dd", HttpStatus.BAD_REQUEST),
    ADDRESS_KEY(1010, "Address field is required", HttpStatus.BAD_REQUEST),
    AVAILABLE_KEY(1011, "Available field is required", HttpStatus.BAD_REQUEST),
    QUANTITY_KEY(1012, "Quantity field is required", HttpStatus.BAD_REQUEST),
    DATE_INCORRECT_FORMAT(1013, "Date is not incorrect format", HttpStatus.BAD_REQUEST),
    BOOK(1013, "Date is not incorrect format", HttpStatus.BAD_REQUEST),

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
