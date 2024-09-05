package com.shep.services;

import com.shep.dto.FreeBookDTO;
import com.shep.entities.FreeBook;
import com.shep.mappers.FreeBookMapper;
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

    @Mock
    private FreeBookMapper freeBookMapper;

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
        Page<FreeBookDTO> freeBookDtos = new PageImpl<>(Collections.emptyList());
        when(freeBookRepository.findAll(pageable)).thenReturn(freeBooks);
        when(freeBookMapper.toDto(any(FreeBook.class))).thenReturn(new FreeBookDTO());

        Page<FreeBookDTO> result = libraryServiceImpl.getAllFreeBooks(pageable);

        assertEquals(freeBookDtos, result);
        verify(freeBookRepository, times(1)).findAll(pageable);
    }

    @Test
    public void testGetFreeBookById() {
        Long id = 1L;
        FreeBook freeBook = new FreeBook();
        FreeBookDTO freeBookDto = new FreeBookDTO();
        when(freeBookRepository.findById(id)).thenReturn(Optional.of(freeBook));
        when(freeBookMapper.toDto(freeBook)).thenReturn(freeBookDto);

        Optional<FreeBookDTO> result = libraryServiceImpl.getFreeBookById(id);

        assertTrue(result.isPresent());
        assertEquals(freeBookDto, result.get());
        verify(freeBookRepository, times(1)).findById(id);
    }

    @Test
    public void testCreateFreeBook() {
        FreeBookDTO freeBookDto = new FreeBookDTO();
        FreeBook freeBook = new FreeBook();
        when(freeBookMapper.toEntity(freeBookDto)).thenReturn(freeBook);
        when(freeBookRepository.save(freeBook)).thenReturn(freeBook);
        when(freeBookMapper.toDto(freeBook)).thenReturn(freeBookDto);

        FreeBookDTO result = libraryServiceImpl.createFreeBook(freeBookDto);

        assertEquals(freeBookDto, result);
        verify(freeBookRepository, times(1)).save(freeBook);
    }

    @Test
    public void testUpdateFreeBook() {
        Long id = 1L;
        FreeBookDTO freeBookDetails = new FreeBookDTO();
        FreeBook existingFreeBook = new FreeBook();
        FreeBookDTO updatedFreeBookDto = new FreeBookDTO();
        when(freeBookRepository.findById(id)).thenReturn(Optional.of(existingFreeBook));
        when(freeBookRepository.save(existingFreeBook)).thenReturn(existingFreeBook);
        when(freeBookMapper.toDto(existingFreeBook)).thenReturn(updatedFreeBookDto);

        Optional<FreeBookDTO> result = libraryServiceImpl.updateFreeBook(id, freeBookDetails);

        assertTrue(result.isPresent());
        assertEquals(updatedFreeBookDto, result.get());
        verify(freeBookRepository, times(1)).findById(id);
        verify(freeBookRepository, times(1)).save(existingFreeBook);
    }

    @Test
    public void testBorrowFreeBookByBookId() {
        Long bookId = 1L;
        FreeBook freeBook = new FreeBook();
        FreeBookDTO freeBookDto = new FreeBookDTO();
        when(freeBookRepository.findByBookId(bookId)).thenReturn(Optional.of(freeBook));
        when(freeBookRepository.save(freeBook)).thenReturn(freeBook);
        when(freeBookMapper.toDto(freeBook)).thenReturn(freeBookDto);

        Optional<FreeBookDTO> result = libraryServiceImpl.borrowFreeBookByBookId(bookId);

        assertTrue(result.isPresent());
        assertEquals(freeBookDto, result.get());
        verify(freeBookRepository, times(1)).findByBookId(bookId);
        verify(freeBookRepository, times(1)).save(freeBook);
    }

    @Test
    public void testReturnFreeBookByBookId() {
        Long bookId = 1L;
        FreeBook freeBook = new FreeBook();
        FreeBookDTO freeBookDto = new FreeBookDTO();
        when(freeBookRepository.findByBookId(bookId)).thenReturn(Optional.of(freeBook));
        when(freeBookRepository.save(freeBook)).thenReturn(freeBook);
        when(freeBookMapper.toDto(freeBook)).thenReturn(freeBookDto);

        Optional<FreeBookDTO> result = libraryServiceImpl.returnFreeBookByBookId(bookId);

        assertTrue(result.isPresent());
        assertEquals(freeBookDto, result.get());
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
