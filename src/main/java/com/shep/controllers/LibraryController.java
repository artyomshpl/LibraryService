package com.shep.controllers;

import com.shep.entities.FreeBook;
import com.shep.services.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/library")
public class LibraryController {
    @Autowired
    private LibraryService libraryService;

    @GetMapping
    public List<FreeBook> getAllFreeBooks() {
        return libraryService.getAllFreeBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FreeBook> getFreeBookById(@PathVariable Long id) {
        FreeBook freeBook = libraryService.getFreeBookById(id);
        if (freeBook != null) {
            return ResponseEntity.ok(freeBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public FreeBook createFreeBook(@RequestBody FreeBook freeBook) {
        return libraryService.createFreeBook(freeBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FreeBook> updateFreeBook(@PathVariable Long id, @RequestBody FreeBook freeBookDetails) {
        FreeBook updatedFreeBook = libraryService.updateFreeBook(id, freeBookDetails);
        if (updatedFreeBook != null) {
            return ResponseEntity.ok(updatedFreeBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFreeBook(@PathVariable Long id) {
        libraryService.deleteFreeBook(id);
        return ResponseEntity.noContent().build();
    }
}
