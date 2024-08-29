package com.shep.controllers.interfaces;

import com.shep.entities.FreeBook;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface LibraryControllerDocs {

    @Operation(summary = "Get all free books with pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Page.class))}),
            @ApiResponse(responseCode = "404", description = "Free books not found", content = @Content)
    })
    @GetMapping
    Page<FreeBook> getAllFreeBooks(Pageable pageable);

    @Operation(summary = "Get a free book by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FreeBook.class))}),
            @ApiResponse(responseCode = "404", description = "Free book not found", content = @Content)
    })
    @GetMapping("/{id}")
    ResponseEntity<FreeBook> getFreeBookById(@PathVariable Long id);

    @Operation(summary = "Create a new free book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FreeBook.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    FreeBook createFreeBook(@RequestBody FreeBook freeBook);

    @Operation(summary = "Update a free book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FreeBook.class))}),
            @ApiResponse(responseCode = "404", description = "Free book not found", content = @Content)
    })
    @PutMapping("/{id}")
    ResponseEntity<FreeBook> updateFreeBook(@PathVariable Long id, @RequestBody FreeBook freeBookDetails);

    @Operation(summary = "Delete a free book by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Free book not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteFreeBook(@PathVariable Long id);

    @Operation(summary = "Delete a free book by book id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Free book not found", content = @Content)
    })
    @DeleteMapping("/book/{bookId}")
    ResponseEntity<Void> deleteFreeBookByBookId(@PathVariable Long bookId);

    @Operation(summary = "Borrow a free book by book ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FreeBook.class))}),
            @ApiResponse(responseCode = "404", description = "Free book not found", content = @Content)
    })
    @PutMapping("/borrow/{bookId}")
    ResponseEntity<FreeBook> borrowFreeBook(@PathVariable Long bookId);

    @Operation(summary = "Return a free book by book ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FreeBook.class))}),
            @ApiResponse(responseCode = "404", description = "Free book not found", content = @Content)
    })
    @PutMapping("/return/{bookId}")
    ResponseEntity<FreeBook> returnFreeBook(@PathVariable Long bookId);
}