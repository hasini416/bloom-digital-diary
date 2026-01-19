package com.Project.Bloom.model;

import com.Project.Bloom.enums.MoodType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "moods",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id","date"})
        }
)
public class Mood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private MoodType moodType;

    private String note;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
