package com.shep.repositories;

import com.shep.entities.FreeBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FreeBookRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FreeBookRepository freeBookRepository;

    private FreeBook freeBook;

    @BeforeEach
    public void setUp() {
        freeBook = new FreeBook();
        freeBook.setBookId(1L);
        entityManager.persist(freeBook);
    }

    @Test
    public void testFindById() {
        Optional<FreeBook> result = freeBookRepository.findById(freeBook.getId());
        assertTrue(result.isPresent());
        assertEquals(freeBook, result.get());
    }

    @Test
    public void testFindByBookId() {
        Optional<FreeBook> result = freeBookRepository.findByBookId(freeBook.getBookId());
        assertTrue(result.isPresent());
        assertEquals(freeBook, result.get());
    }

    @Test
    public void testDeleteByBookId() {
        freeBookRepository.deleteByBookId(freeBook.getBookId());
        Optional<FreeBook> result = freeBookRepository.findByBookId(freeBook.getBookId());
        assertFalse(result.isPresent());
    }
}