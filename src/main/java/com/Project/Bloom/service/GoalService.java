package com.Project.Bloom.service;

import com.Project.Bloom.enums.GoalStatus;
import com.Project.Bloom.model.Goal;
import com.Project.Bloom.model.User;
import com.Project.Bloom.repository.GoalRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoalService {

    private final GoalRepository goalRepository;

    public Goal createGoal(User user, Goal goal) {
        goal.setUser(user);
        goal.setStatus(GoalStatus.ACTIVE);
        return goalRepository.save(goal);
    }

    public List<Goal> getActiveGoals(User user) {
        return goalRepository.findByUserAndStatus(user, GoalStatus.ACTIVE);
    }

    public Goal completeGoal(User user, Integer id) {
        Goal goal = goalRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Goal not found"));

        if (!goal.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }

        goal.setStatus(GoalStatus.COMPLETED);
        return goalRepository.save(goal);
    }
}
