package com.shep.controllers.impl;

import com.shep.controllers.interfaces.LibraryControllerDocs;
import com.shep.dto.FreeBookDTO;
import com.shep.services.impl.LibraryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/library")
@RequiredArgsConstructor
public class LibraryControllerImpl implements LibraryControllerDocs {

    private final LibraryServiceImpl libraryServiceImpl;

    @Override
    public Page<FreeBookDTO> getAllFreeBooks(Pageable pageable) {
        return libraryServiceImpl.getAllFreeBooks(pageable);
    }

    @Override
    public ResponseEntity<FreeBookDTO> getFreeBookById(@PathVariable Long id) {
        Optional<FreeBookDTO> freeBook = libraryServiceImpl.getFreeBookById(id);
        return freeBook.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public FreeBookDTO createFreeBook(@RequestBody FreeBookDTO freeBookDto) {
        return libraryServiceImpl.createFreeBook(freeBookDto);
    }

    @Override
    public ResponseEntity<FreeBookDTO> updateFreeBook(@PathVariable Long id, @RequestBody FreeBookDTO freeBookDetails) {
        Optional<FreeBookDTO> updatedFreeBook = libraryServiceImpl.updateFreeBook(id, freeBookDetails);
        return updatedFreeBook.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Void> deleteFreeBook(@PathVariable Long id) {
        libraryServiceImpl.deleteFreeBook(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteFreeBookByBookId(@PathVariable Long bookId) {
        libraryServiceImpl.deleteFreeBookByBookId(bookId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<FreeBookDTO> borrowFreeBook(@PathVariable Long bookId) {
        Optional<FreeBookDTO> borrowedFreeBook = libraryServiceImpl.borrowFreeBookByBookId(bookId);
        return borrowedFreeBook.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<FreeBookDTO> returnFreeBook(@PathVariable Long bookId) {
        Optional<FreeBookDTO> returnedFreeBook = libraryServiceImpl.returnFreeBookByBookId(bookId);
        return returnedFreeBook.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
