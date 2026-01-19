package com.Project.Bloom.service;

import com.Project.Bloom.model.Habit;
import com.Project.Bloom.model.HabitLog;
import com.Project.Bloom.model.User;
import com.Project.Bloom.repository.HabitLogRepository;
import com.Project.Bloom.repository.HabitRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitService {

    private final HabitRepository habitRepository;
    private final HabitLogRepository habitLogRepository;

    public Habit createhabit(User user, String title) {
        Habit habit = new Habit();
        habit.setTitle(title);
        habit.setUser(user);
        habit.setCreated_at(LocalDate.now());

        return habitRepository.save(habit);
    }

    public List<Habit> getUserHabits(User user) {
        return habitRepository.findByUser(user);
    }

    public HabitLog toggleTodayHabit(User user, Long habitId, boolean completed) {
        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new RuntimeException("habit not found"));

        if (!habit.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }

        LocalDate today = LocalDate.now();

        HabitLog habitLog = habitLogRepository
                .findByHabitAndDate(habit,today)
                .orElse(new HabitLog());

        habitLog.setHabit(habit);
        habitLog.setCompleted(completed);
        habitLog.setDate(today);

        return habitLogRepository.save(habitLog);
    }
}
