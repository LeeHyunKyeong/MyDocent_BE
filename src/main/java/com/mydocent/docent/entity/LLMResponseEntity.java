package com.mydocent.docent.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "llm_answer")
public class LLMResponseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String artworkTitle;

    @Column
    private String artistName;

    @Column(columnDefinition = "TEXT")
    private String artworkDescription;

    @Column(columnDefinition = "TEXT")
    private String artistDescription;

    @Column(columnDefinition = "TEXT")
    private String artworkBackground;

    @Column(columnDefinition = "TEXT")
    private String appreciationPoint;

    @Column(columnDefinition = "TEXT")
    private String artHistory;

    @Column(columnDefinition = "TEXT")
    private String citations; // 출처 통째로 JSON 문자열로 저장

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    public LLMResponseEntity(
            String artistName,
            String artworkTitle,
            String artworkDescription,
            String artistDescription,
            String artworkBackground,
            String appreciationPoint,
            String artHistory,
            String citations
    ) {
        this.artistName = artistName;
        this.artworkTitle = artworkTitle;
        this.artworkDescription = artworkDescription;
        this.artistDescription = artistDescription;
        this.artworkBackground = artworkBackground;
        this.appreciationPoint = appreciationPoint;
        this.artHistory = artHistory;
        this.citations = citations;
        this.createdAt = LocalDateTime.now();
    }
}
