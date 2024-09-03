package com.shep.services.impl;

import com.shep.entities.FreeBook;
import com.shep.exceptions.NotFoundException;
import com.shep.repositories.FreeBookRepository;
import com.shep.services.interfaces.LibraryServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryServiceInterface {
    private final FreeBookRepository freeBookRepository;

    @Override
    public Page<FreeBook> getAllFreeBooks(Pageable pageable) {
        return freeBookRepository.findAll(pageable);
    }

    @Override
    public Optional<FreeBook> getFreeBookById(Long id) {
        return freeBookRepository.findById(id);
    }

    @Override
    public FreeBook createFreeBook(FreeBook freeBook) {
        return freeBookRepository.save(freeBook);
    }

    @Override
    public Optional<FreeBook> updateFreeBook(Long id, FreeBook freeBookDetails) {
        return Optional.ofNullable(freeBookRepository.findById(id).map(freeBook -> {
            freeBook.setBookId(freeBookDetails.getBookId());
            freeBook.setBorrowedTime(freeBookDetails.getBorrowedTime());
            freeBook.setReturnTime(freeBookDetails.getReturnTime());
            return freeBookRepository.save(freeBook);
        }).orElseThrow(() -> new NotFoundException("Free book not found with id " + id)));
    }

    @Override
    public Optional<FreeBook> borrowFreeBookByBookId(Long bookId) {
        return Optional.ofNullable(freeBookRepository.findByBookId(bookId).map(freeBook -> {
            freeBook.setBorrowedTime(LocalDateTime.now());
            return freeBookRepository.save(freeBook);
        }).orElseThrow(() -> new NotFoundException("Free book not found with bookId " + bookId)));
    }

    @Override
    public Optional<FreeBook> returnFreeBookByBookId(Long bookId) {
        return Optional.ofNullable(freeBookRepository.findByBookId(bookId).map(freeBook -> {
            freeBook.setReturnTime(LocalDateTime.now());
            return freeBookRepository.save(freeBook);
        }).orElseThrow(() -> new NotFoundException("Free book not found with bookId " + bookId)));
    }

    @Override
    public void deleteFreeBook(Long id) {
        if (!freeBookRepository.existsById(id)) {
            throw new NotFoundException("Free book not found with id " + id);
        }
        freeBookRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteFreeBookByBookId(Long bookId) {
        Optional<FreeBook> freeBook = freeBookRepository.findByBookId(bookId);
        if (freeBook.isPresent()) {
            freeBookRepository.deleteByBookId(bookId);
        } else {
            throw new NotFoundException("Free book not found with bookId " + bookId);
        }
    }
}
