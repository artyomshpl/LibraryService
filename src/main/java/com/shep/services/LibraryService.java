package com.shep.services;


import com.shep.entities.FreeBook;
import com.shep.repositories.FreeBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LibraryService {
    @Autowired
    private FreeBookRepository freeBookRepository;

    public Page<FreeBook> getAllFreeBooks(Pageable pageable) {
        return freeBookRepository.findAll(pageable);
    }

    public Optional<FreeBook> getFreeBookById(Long id) {
        return freeBookRepository.findById(id);
    }

    public FreeBook createFreeBook(FreeBook freeBook) {
        return freeBookRepository.save(freeBook);
    }

    public Optional<FreeBook> updateFreeBook(Long id, FreeBook freeBookDetails) {
        return freeBookRepository.findById(id).map(freeBook -> {
            freeBook.setBookId(freeBookDetails.getBookId());
            freeBook.setBorrowedTime(freeBookDetails.getBorrowedTime());
            freeBook.setReturnTime(freeBookDetails.getReturnTime());
            return freeBookRepository.save(freeBook);
        });
    }

    public void deleteFreeBook(Long id) {
        freeBookRepository.deleteById(id);
    }
}
