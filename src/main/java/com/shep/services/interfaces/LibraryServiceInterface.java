package com.shep.services.interfaces;

import com.shep.entities.FreeBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface LibraryServiceInterface {
    Page<FreeBook> getAllFreeBooks(Pageable pageable);
    Optional<FreeBook> getFreeBookById(Long id);
    FreeBook createFreeBook(FreeBook freeBook);
    Optional<FreeBook> updateFreeBook(Long id, FreeBook freeBookDetails);
    Optional<FreeBook> borrowFreeBookByBookId(Long bookId);
    Optional<FreeBook> returnFreeBookByBookId(Long bookId);
    void deleteFreeBook(Long id);
    void deleteFreeBookByBookId(Long bookId);
}