package com.Project.Bloom.service;

import com.Project.Bloom.enums.MoodType;
import com.Project.Bloom.model.Mood;
import com.Project.Bloom.model.User;
import com.Project.Bloom.repository.MoodRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MoodService {

    private final MoodRepository moodRepository;

    public MoodService(MoodRepository moodRepository){
        this.moodRepository = moodRepository;
    }


    public Mood saveMood(User user, MoodType moodType, String note) {
        LocalDate today = LocalDate.now();

        Mood mood = moodRepository
                .findByUserAndDate(user,today)
                .orElse(new Mood());

        mood.setUser(user);
        mood.setDate(today);
        mood.setMoodType(moodType);
        mood.setNote(note);

        return moodRepository.save(mood);

    }

    public Mood getTodayMood(User user) {
        return moodRepository
                .findByUserAndDate(user,LocalDate.now())
                .orElse(null);
    }
}

