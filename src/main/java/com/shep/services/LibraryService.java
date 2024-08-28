package com.shep.services;


import com.shep.entities.FreeBook;
import com.shep.repositories.FreeBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LibraryService {
    @Autowired
    private FreeBookRepository freeBookRepository;

    public Page<FreeBook> getAllFreeBooks(Pageable pageable) {
        return freeBookRepository.findAll(pageable);
    }

    public FreeBook getFreeBookById(Long id) {
        return freeBookRepository.findById(id).orElse(null);
    }

    public FreeBook createFreeBook(FreeBook freeBook) {
        return freeBookRepository.save(freeBook);
    }

    public FreeBook updateFreeBook(Long id, FreeBook freeBookDetails) {
        FreeBook freeBook = freeBookRepository.findById(id).orElse(null);
        if (freeBook != null) {
            freeBook.setBookId(freeBookDetails.getBookId());
            freeBook.setBorrowedTime(freeBookDetails.getBorrowedTime());
            freeBook.setReturnTime(freeBookDetails.getReturnTime());
            return freeBookRepository.save(freeBook);
        }
        return null;
    }

    public void deleteFreeBook(Long id) {
        freeBookRepository.deleteById(id);
    }
}
