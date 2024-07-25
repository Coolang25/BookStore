package com.trinity.bookstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;

@Getter
public enum ErrorCode {
    AUTHOR_NOT_EXISTED(1001, "Author not existed", HttpStatus.NOT_FOUND),
    BOOK_NOT_EXISTED(1002, "Book not existed", HttpStatus.NOT_FOUND),
    AUTHOR_EXISTED(1003, "Author is existed", HttpStatus.BAD_REQUEST),
    BOOK_EXISTED(1004, "Book  is existed", HttpStatus.BAD_REQUEST),
    UNCATEGORIZED_EXCEPTION(1005, "Uncategoried exception", HttpStatus.BAD_REQUEST),
    INVALID_KEY(1006, "Invalid key", HttpStatus.BAD_REQUEST),
    INVALID_TITLE(1007, "Title field is required", HttpStatus.BAD_REQUEST),
    INVALID_FULL_NAME(1008, "Full name field is required", HttpStatus.BAD_REQUEST),
    INVALID_DATE(1009, "Field is required, yyyy-MM-dd", HttpStatus.BAD_REQUEST),
    INVALID_ADDRESS(1010, "Address field is required", HttpStatus.BAD_REQUEST),
    INVALID_AVAILABLE(1011, "Available field is required", HttpStatus.BAD_REQUEST),
    INVALID_QUANTITY(1012, "Quantity field is required", HttpStatus.BAD_REQUEST),
    INVALID_EMAIL(1013, "Email field is required and has *@*.* format ", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1014, "Password must be at least 8 characters", HttpStatus.BAD_REQUEST),
    DATE_INCORRECT_FORMAT(1015, "Date is not incorrect format", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1016, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1017, "Unauthorized", HttpStatus.FORBIDDEN),
    USER_NOT_EXISTED(1018, "User not existed", HttpStatus.NOT_FOUND),
    USER_EXISTED(1019, "User existed", HttpStatus.BAD_REQUEST),
    USER_NOT_BORROW_BOOK(1020, "User didn't borrow this book", HttpStatus.OK),
    USER_RETURNED_BOOK(1021, "User returned this book", HttpStatus.OK),
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
