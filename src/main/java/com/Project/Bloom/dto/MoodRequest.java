package com.Project.Bloom.dto;

import com.Project.Bloom.enums.MoodType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoodRequest {
    private MoodType moodType;
    private String note;
}
