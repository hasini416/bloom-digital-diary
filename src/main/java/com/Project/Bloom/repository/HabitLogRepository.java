package com.Project.Bloom.repository;


import com.Project.Bloom.model.Habit;
import com.Project.Bloom.model.HabitLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface HabitLogRepository extends JpaRepository<HabitLog,Long> {

    Optional<HabitLog> findByHabitAndDate(
            Habit habit,
            LocalDate date
    );
}
