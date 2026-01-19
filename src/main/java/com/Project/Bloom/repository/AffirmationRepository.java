package com.Project.Bloom.repository;

import com.Project.Bloom.enums.AffirmationCategory;
import com.Project.Bloom.model.Affirmation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AffirmationRepository extends JpaRepository<Affirmation, Long> {

    @Query(value = "SELECT * FROM affirmation ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Affirmation findRandom();

    @Query(value = "SELECT * FROM affirmation WHERE category = :category ORDER BY RANDOM() LIMIT 1",
    nativeQuery = true)
    Affirmation findRandomByCategory(@Param("category") AffirmationCategory category);
}
