package com.Project.Bloom.repository;

import com.Project.Bloom.model.Habit;
import com.Project.Bloom.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitRepository  extends JpaRepository<Habit,Long> {
    List<Habit> findByUser(User user);
}
