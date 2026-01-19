package com.Project.Bloom.repository;

import com.Project.Bloom.model.JournalEntry;
import com.Project.Bloom.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface JournalRepository extends JpaRepository<JournalEntry,Long> {
    Optional<JournalEntry> findByUserAndDate(User user, LocalDate date);
}
