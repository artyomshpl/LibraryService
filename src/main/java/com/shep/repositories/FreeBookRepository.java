package com.shep.repositories;

import com.shep.entities.FreeBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FreeBookRepository extends JpaRepository<FreeBook, Long> {
    Optional<FreeBook> findById(Long id);
}
