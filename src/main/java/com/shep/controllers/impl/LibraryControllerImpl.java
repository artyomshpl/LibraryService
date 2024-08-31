package com.shep.controllers.impl;

import com.shep.controllers.interfaces.LibraryControllerDocs;
import com.shep.entities.FreeBook;
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
    public Page<FreeBook> getAllFreeBooks(Pageable pageable) {
        return libraryServiceImpl.getAllFreeBooks(pageable);
    }

    @Override
    public ResponseEntity<FreeBook> getFreeBookById(@PathVariable Long id) {
        Optional<FreeBook> freeBook = libraryServiceImpl.getFreeBookById(id);
        return freeBook.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public FreeBook createFreeBook(@RequestBody FreeBook freeBook) {
        return libraryServiceImpl.createFreeBook(freeBook);
    }

    @Override
    public ResponseEntity<FreeBook> updateFreeBook(@PathVariable Long id, @RequestBody FreeBook freeBookDetails) {
        Optional<FreeBook> updatedFreeBook = libraryServiceImpl.updateFreeBook(id, freeBookDetails);
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
    public ResponseEntity<FreeBook> borrowFreeBook(@PathVariable Long bookId) {
        Optional<FreeBook> borrowedFreeBook = libraryServiceImpl.borrowFreeBookByBookId(bookId);
        return borrowedFreeBook.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<FreeBook> returnFreeBook(@PathVariable Long bookId) {
        Optional<FreeBook> returnedFreeBook = libraryServiceImpl.returnFreeBookByBookId(bookId);
        return returnedFreeBook.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
