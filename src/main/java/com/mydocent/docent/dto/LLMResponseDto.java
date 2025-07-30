package com.mydocent.docent.dto;

import java.time.LocalDateTime;

public record LLMResponseDto(
        Long id,
        String artistName,
        String artworkTitle,
        String artworkDescription,
        String artistDescription,
        String artworkBackground,
        String appreciationPoint,
        String artHistory,
        LocalDateTime createdAt
) {}