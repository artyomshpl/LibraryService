package com.shep.controllers;

import com.shep.controllers.impl.LibraryControllerImpl;
import com.shep.dataTransferObjects.FreeBookDTO;
import com.shep.services.implementation.LibraryServiceImpl;
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
        Page<FreeBookDTO> freeBooks = new PageImpl<>(Collections.emptyList());
        when(libraryServiceImpl.getAllFreeBooks(pageable)).thenReturn(freeBooks);

        Page<FreeBookDTO> result = libraryControllerImpl.getAllFreeBooks(pageable);

        assertEquals(freeBooks, result);
        verify(libraryServiceImpl, times(1)).getAllFreeBooks(pageable);
    }

    @Test
    public void testGetFreeBookById() {
        Long id = 1L;
        FreeBookDTO freeBookDto = new FreeBookDTO();
        when(libraryServiceImpl.getFreeBookById(id)).thenReturn(Optional.of(freeBookDto));

        ResponseEntity<FreeBookDTO> result = libraryControllerImpl.getFreeBookById(id);

        assertEquals(ResponseEntity.ok(freeBookDto), result);
        verify(libraryServiceImpl, times(1)).getFreeBookById(id);
    }

    @Test
    public void testCreateFreeBook() {
        FreeBookDTO freeBookDto = new FreeBookDTO();
        when(libraryServiceImpl.createFreeBook(freeBookDto)).thenReturn(freeBookDto);

        FreeBookDTO result = libraryControllerImpl.createFreeBook(freeBookDto);

        assertEquals(freeBookDto, result);
        verify(libraryServiceImpl, times(1)).createFreeBook(freeBookDto);
    }

    @Test
    public void testUpdateFreeBook() {
        Long id = 1L;
        FreeBookDTO freeBookDetails = new FreeBookDTO();
        FreeBookDTO updatedFreeBook = new FreeBookDTO();
        when(libraryServiceImpl.updateFreeBook(id, freeBookDetails)).thenReturn(Optional.of(updatedFreeBook));

        ResponseEntity<FreeBookDTO> result = libraryControllerImpl.updateFreeBook(id, freeBookDetails);

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
        FreeBookDTO borrowedFreeBook = new FreeBookDTO();
        when(libraryServiceImpl.borrowFreeBookByBookId(bookId)).thenReturn(Optional.of(borrowedFreeBook));

        ResponseEntity<FreeBookDTO> result = libraryControllerImpl.borrowFreeBook(bookId);

        assertEquals(ResponseEntity.ok(borrowedFreeBook), result);
        verify(libraryServiceImpl, times(1)).borrowFreeBookByBookId(bookId);
    }

    @Test
    public void testReturnFreeBook() {
        Long bookId = 1L;
        FreeBookDTO returnedFreeBook = new FreeBookDTO();
        when(libraryServiceImpl.returnFreeBookByBookId(bookId)).thenReturn(Optional.of(returnedFreeBook));

        ResponseEntity<FreeBookDTO> result = libraryControllerImpl.returnFreeBook(bookId);

        assertEquals(ResponseEntity.ok(returnedFreeBook), result);
        verify(libraryServiceImpl, times(1)).returnFreeBookByBookId(bookId);
    }
}
