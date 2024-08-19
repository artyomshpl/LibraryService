package com.shep.controllers;

import com.shep.entities.FreeBook;
import com.shep.services.LibraryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library")
public class LibraryController {
    @Autowired
    private LibraryService libraryService;

    @Operation(summary = "Get all free books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FreeBook.class))}),
            @ApiResponse(responseCode = "404", description = "Free books not found", content = @Content)
    })
    @GetMapping
    public List<FreeBook> getAllFreeBooks() {
        return libraryService.getAllFreeBooks();
    }

    @Operation(summary = "Get a free book by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FreeBook.class))}),
            @ApiResponse(responseCode = "404", description = "Free book not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<FreeBook> getFreeBookById(@PathVariable Long id) {
        FreeBook freeBook = libraryService.getFreeBookById(id);
        if (freeBook != null) {
            return ResponseEntity.ok(freeBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Create a new free book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FreeBook.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public FreeBook createFreeBook(@RequestBody FreeBook freeBook) {
        return libraryService.createFreeBook(freeBook);
    }

    @Operation(summary = "Update a free book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FreeBook.class))}),
            @ApiResponse(responseCode = "404", description = "Free book not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<FreeBook> updateFreeBook(@PathVariable Long id, @RequestBody FreeBook freeBookDetails) {
        FreeBook updatedFreeBook = libraryService.updateFreeBook(id, freeBookDetails);
        if (updatedFreeBook != null) {
            return ResponseEntity.ok(updatedFreeBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete a free book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Free book not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFreeBook(@PathVariable Long id) {
        libraryService.deleteFreeBook(id);
        return ResponseEntity.noContent().build();
    }
}
