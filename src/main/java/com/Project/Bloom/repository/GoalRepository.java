package com.Project.Bloom.repository;

import com.Project.Bloom.enums.GoalStatus;
import com.Project.Bloom.model.Goal;
import com.Project.Bloom.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Integer> {
    List<Goal> findByUserAndStatus(User user, GoalStatus status);

}
