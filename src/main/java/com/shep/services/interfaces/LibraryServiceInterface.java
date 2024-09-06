package com.shep.services.interfaces;

import com.shep.dataTransferObjects.FreeBookDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface LibraryServiceInterface {
    Page<FreeBookDTO> getAllFreeBooks(Pageable pageable);
    Optional<FreeBookDTO> getFreeBookById(Long id);
    FreeBookDTO createFreeBook(FreeBookDTO freeBookDto);
    Optional<FreeBookDTO> updateFreeBook(Long id, FreeBookDTO freeBookDetails);
    Optional<FreeBookDTO> borrowFreeBookByBookId(Long bookId);
    Optional<FreeBookDTO> returnFreeBookByBookId(Long bookId);
    void deleteFreeBook(Long id);
    void deleteFreeBookByBookId(Long bookId);
}
