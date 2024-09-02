package com.shep.services;

import com.shep.entities.FreeBook;
import com.shep.repositories.FreeBookRepository;
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

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LibraryServiceImplTest {

    @Mock
    private FreeBookRepository freeBookRepository;

    @InjectMocks
    private LibraryServiceImpl libraryServiceImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllFreeBooks() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<FreeBook> freeBooks = new PageImpl<>(Collections.emptyList());
        when(freeBookRepository.findAll(pageable)).thenReturn(freeBooks);

        Page<FreeBook> result = libraryServiceImpl.getAllFreeBooks(pageable);

        assertEquals(freeBooks, result);
        verify(freeBookRepository, times(1)).findAll(pageable);
    }

    @Test
    public void testGetFreeBookById() {
        Long id = 1L;
        FreeBook freeBook = new FreeBook();
        when(freeBookRepository.findById(id)).thenReturn(Optional.of(freeBook));

        Optional<FreeBook> result = libraryServiceImpl.getFreeBookById(id);

        assertTrue(result.isPresent());
        assertEquals(freeBook, result.get());
        verify(freeBookRepository, times(1)).findById(id);
    }

    @Test
    public void testCreateFreeBook() {
        FreeBook freeBook = new FreeBook();
        when(freeBookRepository.save(freeBook)).thenReturn(freeBook);

        FreeBook result = libraryServiceImpl.createFreeBook(freeBook);

        assertEquals(freeBook, result);
        verify(freeBookRepository, times(1)).save(freeBook);
    }

    @Test
    public void testUpdateFreeBook() {
        Long id = 1L;
        FreeBook freeBookDetails = new FreeBook();
        FreeBook existingFreeBook = new FreeBook();
        when(freeBookRepository.findById(id)).thenReturn(Optional.of(existingFreeBook));
        when(freeBookRepository.save(existingFreeBook)).thenReturn(existingFreeBook);

        Optional<FreeBook> result = libraryServiceImpl.updateFreeBook(id, freeBookDetails);

        assertTrue(result.isPresent());
        assertEquals(existingFreeBook, result.get());
        verify(freeBookRepository, times(1)).findById(id);
        verify(freeBookRepository, times(1)).save(existingFreeBook);
    }

    @Test
    public void testBorrowFreeBookByBookId() {
        Long bookId = 1L;
        FreeBook freeBook = new FreeBook();
        when(freeBookRepository.findByBookId(bookId)).thenReturn(Optional.of(freeBook));
        when(freeBookRepository.save(freeBook)).thenReturn(freeBook);

        Optional<FreeBook> result = libraryServiceImpl.borrowFreeBookByBookId(bookId);

        assertTrue(result.isPresent());
        assertEquals(freeBook, result.get());
        verify(freeBookRepository, times(1)).findByBookId(bookId);
        verify(freeBookRepository, times(1)).save(freeBook);
    }

    @Test
    public void testReturnFreeBookByBookId() {
        Long bookId = 1L;
        FreeBook freeBook = new FreeBook();
        when(freeBookRepository.findByBookId(bookId)).thenReturn(Optional.of(freeBook));
        when(freeBookRepository.save(freeBook)).thenReturn(freeBook);

        Optional<FreeBook> result = libraryServiceImpl.returnFreeBookByBookId(bookId);

        assertTrue(result.isPresent());
        assertEquals(freeBook, result.get());
        verify(freeBookRepository, times(1)).findByBookId(bookId);
        verify(freeBookRepository, times(1)).save(freeBook);
    }

    @Test
    public void testDeleteFreeBook() {
        Long id = 1L;
        when(freeBookRepository.existsById(id)).thenReturn(true);
        doNothing().when(freeBookRepository).deleteById(id);

        assertDoesNotThrow(() -> libraryServiceImpl.deleteFreeBook(id));
        verify(freeBookRepository, times(1)).existsById(id);
        verify(freeBookRepository, times(1)).deleteById(id);
    }
}