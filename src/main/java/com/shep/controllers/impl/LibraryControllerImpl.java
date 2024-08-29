package com.shep.controllers.impl;

import com.shep.controllers.interfaces.LibraryControllerDocs;
import com.shep.entities.FreeBook;
import com.shep.services.LibraryService;
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

    private final LibraryService libraryService;

    @Override
    public Page<FreeBook> getAllFreeBooks(Pageable pageable) {
        return libraryService.getAllFreeBooks(pageable);
    }

    @Override
    public ResponseEntity<FreeBook> getFreeBookById(@PathVariable Long id) {
        Optional<FreeBook> freeBook = libraryService.getFreeBookById(id);
        return freeBook.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public FreeBook createFreeBook(@RequestBody FreeBook freeBook) {
        return libraryService.createFreeBook(freeBook);
    }

    @Override
    public ResponseEntity<FreeBook> updateFreeBook(@PathVariable Long id, @RequestBody FreeBook freeBookDetails) {
        Optional<FreeBook> updatedFreeBook = libraryService.updateFreeBook(id, freeBookDetails);
        return updatedFreeBook.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Void> deleteFreeBook(@PathVariable Long id) {
        libraryService.deleteFreeBook(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteFreeBookByBookId(@PathVariable Long bookId) {
        libraryService.deleteFreeBookByBookId(bookId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<FreeBook> borrowFreeBook(@PathVariable Long bookId) {
        Optional<FreeBook> borrowedFreeBook = libraryService.borrowFreeBookByBookId(bookId);
        return borrowedFreeBook.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<FreeBook> returnFreeBook(@PathVariable Long bookId) {
        Optional<FreeBook> returnedFreeBook = libraryService.returnFreeBookByBookId(bookId);
        return returnedFreeBook.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}