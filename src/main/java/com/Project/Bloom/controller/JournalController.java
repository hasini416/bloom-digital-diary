package com.Project.Bloom.controller;

import com.Project.Bloom.dto.JournalRequest;
import com.Project.Bloom.model.JournalEntry;
import com.Project.Bloom.model.User;
import com.Project.Bloom.service.JournalService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/journal")
@RequiredArgsConstructor
public class JournalController {

    private final JournalService journalService;

    @PostMapping
    public ResponseEntity<JournalEntry> saveEntry(
            @RequestBody JournalRequest journalRequest, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(journalService.saveTodayEntry(user, journalRequest.getContent()));
    }

    @GetMapping("/today")
    public ResponseEntity<JournalEntry> getTodayEntry(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(journalService.getTodayEntry(user));
    }
}
