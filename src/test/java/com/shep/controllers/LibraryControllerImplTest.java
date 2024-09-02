package com.shep.controllers;

import com.shep.controllers.impl.LibraryControllerImpl;
import com.shep.entities.FreeBook;
import com.shep.services.impl.LibraryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LibraryControllerImplTest {

    @Mock
    private LibraryServiceImpl libraryServiceImpl;

    @InjectMocks
    private LibraryControllerImpl libraryControllerImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllFreeBooks() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<FreeBook> freeBooks = new PageImpl<>(Collections.emptyList());
        when(libraryServiceImpl.getAllFreeBooks(pageable)).thenReturn(freeBooks);

        Page<FreeBook> result = libraryControllerImpl.getAllFreeBooks(pageable);

        assertEquals(freeBooks, result);
        verify(libraryServiceImpl, times(1)).getAllFreeBooks(pageable);
    }

    @Test
    public void testGetFreeBookById() {
        Long id = 1L;
        FreeBook freeBook = new FreeBook();
        when(libraryServiceImpl.getFreeBookById(id)).thenReturn(Optional.of(freeBook));

        ResponseEntity<FreeBook> result = libraryControllerImpl.getFreeBookById(id);

        assertEquals(ResponseEntity.ok(freeBook), result);
        verify(libraryServiceImpl, times(1)).getFreeBookById(id);
    }

    @Test
    public void testCreateFreeBook() {
        FreeBook freeBook = new FreeBook();
        when(libraryServiceImpl.createFreeBook(freeBook)).thenReturn(freeBook);

        FreeBook result = libraryControllerImpl.createFreeBook(freeBook);

        assertEquals(freeBook, result);
        verify(libraryServiceImpl, times(1)).createFreeBook(freeBook);
    }

    @Test
    public void testUpdateFreeBook() {
        Long id = 1L;
        FreeBook freeBookDetails = new FreeBook();
        FreeBook updatedFreeBook = new FreeBook();
        when(libraryServiceImpl.updateFreeBook(id, freeBookDetails)).thenReturn(Optional.of(updatedFreeBook));

        ResponseEntity<FreeBook> result = libraryControllerImpl.updateFreeBook(id, freeBookDetails);

        assertEquals(ResponseEntity.ok(updatedFreeBook), result);
        verify(libraryServiceImpl, times(1)).updateFreeBook(id, freeBookDetails);
    }

    @Test
    public void testDeleteFreeBook() {
        Long id = 1L;
        doNothing().when(libraryServiceImpl).deleteFreeBook(id);

        ResponseEntity<Void> result = libraryControllerImpl.deleteFreeBook(id);

        assertEquals(ResponseEntity.noContent().build(), result);
        verify(libraryServiceImpl, times(1)).deleteFreeBook(id);
    }

    @Test
    public void testDeleteFreeBookByBookId() {
        Long bookId = 1L;
        doNothing().when(libraryServiceImpl).deleteFreeBookByBookId(bookId);

        ResponseEntity<Void> result = libraryControllerImpl.deleteFreeBookByBookId(bookId);

        assertEquals(ResponseEntity.noContent().build(), result);
        verify(libraryServiceImpl, times(1)).deleteFreeBookByBookId(bookId);
    }

    @Test
    public void testBorrowFreeBook() {
        Long bookId = 1L;
        FreeBook borrowedFreeBook = new FreeBook();
        when(libraryServiceImpl.borrowFreeBookByBookId(bookId)).thenReturn(Optional.of(borrowedFreeBook));

        ResponseEntity<FreeBook> result = libraryControllerImpl.borrowFreeBook(bookId);

        assertEquals(ResponseEntity.ok(borrowedFreeBook), result);
        verify(libraryServiceImpl, times(1)).borrowFreeBookByBookId(bookId);
    }

    @Test
    public void testReturnFreeBook() {
        Long bookId = 1L;
        FreeBook returnedFreeBook = new FreeBook();
        when(libraryServiceImpl.returnFreeBookByBookId(bookId)).thenReturn(Optional.of(returnedFreeBook));

        ResponseEntity<FreeBook> result = libraryControllerImpl.returnFreeBook(bookId);

        assertEquals(ResponseEntity.ok(returnedFreeBook), result);
        verify(libraryServiceImpl, times(1)).returnFreeBookByBookId(bookId);
    }
}