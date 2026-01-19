package com.Project.Bloom.service;

import com.Project.Bloom.dto.AffirmationResponse;
import com.Project.Bloom.enums.AffirmationCategory;
import com.Project.Bloom.enums.MoodType;
import com.Project.Bloom.model.Affirmation;
import com.Project.Bloom.model.Mood;
import com.Project.Bloom.model.User;
import com.Project.Bloom.repository.AffirmationRepository;
import com.Project.Bloom.repository.MoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class AffirmationServiceImpl implements AffirmationService {

    private final AffirmationRepository affirmationRepository;
    private final MoodRepository moodRepository;

    public AffirmationServiceImpl(
            AffirmationRepository affirmationRepository,
            MoodRepository moodRepository) {
        this.affirmationRepository = affirmationRepository;
        this.moodRepository = moodRepository;
    }

    @Override
    public AffirmationResponse getTodayAffirmation(User user) {

        if (user == null) {
            return new AffirmationResponse(
                    0L,
                    "You are learning and growing 🌱"
            );
        }

        LocalDate today = LocalDate.now();
        Optional<Mood> moodOpt = moodRepository.findByUserAndDate(user, today);

        Affirmation affirmation;

        if (moodOpt.isPresent()) {
            MoodType moodType = moodOpt.get().getMoodType();
            affirmation = affirmationRepository
                    .findRandomByCategory(mapMoodToCategory(moodType));
        } else {
            affirmation = affirmationRepository.findRandom();
        }

        if (affirmation == null) {
            return new AffirmationResponse(
                    0L,
                    "You showed up today, and that matters 🌸"
            );
        }

        return new AffirmationResponse(
                affirmation.getId(),
                affirmation.getText()
        );
    }

    private AffirmationCategory mapMoodToCategory(MoodType mood) {
        return switch (mood) {
            case SAD -> AffirmationCategory.CONFIDENCE;
            case STRESSED -> AffirmationCategory.CLAM;
            case HAPPY -> AffirmationCategory.GRATITUDE;
            default -> AffirmationCategory.MOTIVATION;
        };
    }
}
