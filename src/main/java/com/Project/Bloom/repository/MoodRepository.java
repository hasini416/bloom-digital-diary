package com.Project.Bloom.repository;

import com.Project.Bloom.model.Mood;
import com.Project.Bloom.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface MoodRepository extends JpaRepository<Mood, Long> {
    Optional<Mood> findByUserAndDate(User user, LocalDate date);
}
