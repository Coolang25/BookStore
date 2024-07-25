package com.trinity.bookstore.mapper;

import com.trinity.bookstore.dto.request.AuthorCreationRequest;
import com.trinity.bookstore.dto.request.AuthorUpdateRequest;
import com.trinity.bookstore.dto.response.AuthorResponse;
import com.trinity.bookstore.dto.response.BorrowingResponse;
import com.trinity.bookstore.entity.Author;
import com.trinity.bookstore.entity.Borrowing;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BorrowingMapper {
    BorrowingResponse toBorrowingResponse(Borrowing borrowing);
}
