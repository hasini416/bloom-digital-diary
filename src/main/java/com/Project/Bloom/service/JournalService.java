package com.Project.Bloom.service;

import com.Project.Bloom.dto.JournalRequest;
import com.Project.Bloom.model.JournalEntry;
import com.Project.Bloom.model.User;
import com.Project.Bloom.repository.JournalRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class JournalService {

    private final JournalRepository journalRepository;

    public JournalEntry saveTodayEntry(User user, String content) {
        LocalDate today = LocalDate.now();

        JournalEntry journalEntry = journalRepository
                .findByUserAndDate(user,today)
                .orElse(new JournalEntry());

        journalEntry.setUser(user);
        journalEntry.setDate(today);
        journalEntry.setContent(content);

        return journalRepository.save(journalEntry);
    }

    public JournalEntry getTodayEntry(User user) {
        LocalDate today = LocalDate.now();

        return journalRepository
                .findByUserAndDate(user,today)
                .orElse(null);
    }
}
