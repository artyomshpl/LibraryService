package com.shep.services.implementation;

import com.shep.dataTransferObjects.FreeBookDTO;
import com.shep.entities.FreeBook;
import com.shep.exceptions.NotFoundException;
import com.shep.mapper.FreeBookMapper;
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
    private final FreeBookMapper freeBookMapper;

    @Override
    public Page<FreeBookDTO> getAllFreeBooks(Pageable pageable) {
        return freeBookRepository.findAll(pageable).map(freeBookMapper::toDto);
    }

    @Override
    public Optional<FreeBookDTO> getFreeBookById(Long id) {
        return freeBookRepository.findById(id).map(freeBookMapper::toDto);
    }

    @Override
    public FreeBookDTO createFreeBook(FreeBookDTO freeBookDto) {
        FreeBook freeBook = freeBookMapper.toEntity(freeBookDto);
        return freeBookMapper.toDto(freeBookRepository.save(freeBook));
    }

    @Override
    public Optional<FreeBookDTO> updateFreeBook(Long id, FreeBookDTO freeBookDetails) {
        return freeBookRepository.findById(id).map(freeBook -> {
            freeBook.setBookId(freeBookDetails.getBookId());
            freeBook.setBorrowedTime(freeBookDetails.getBorrowedTime());
            freeBook.setReturnTime(freeBookDetails.getReturnTime());
            return freeBookMapper.toDto(freeBookRepository.save(freeBook));
        }).map(Optional::of).orElseThrow(() -> new NotFoundException("Free book not found with id " + id));
    }

    @Override
    public Optional<FreeBookDTO> borrowFreeBookByBookId(Long bookId) {
        return freeBookRepository.findByBookId(bookId).map(freeBook -> {
            freeBook.setBorrowedTime(LocalDateTime.now());
            freeBook.setReturnTime(null);
            return freeBookMapper.toDto(freeBookRepository.save(freeBook));
        }).map(Optional::of).orElseThrow(() -> new NotFoundException("Free book not found with bookId " + bookId));
    }

    @Override
    public Optional<FreeBookDTO> returnFreeBookByBookId(Long bookId) {
        return freeBookRepository.findByBookId(bookId).map(freeBook -> {
            freeBook.setBorrowedTime(null);
            freeBook.setReturnTime(LocalDateTime.now());
            return freeBookMapper.toDto(freeBookRepository.save(freeBook));
        }).map(Optional::of).orElseThrow(() -> new NotFoundException("Free book not found with bookId " + bookId));
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
