package com.Project.Bloom.service;

import com.Project.Bloom.dto.AffirmationResponse;
import com.Project.Bloom.model.User;

public interface AffirmationService {
    AffirmationResponse getTodayAffirmation(User user);
}
