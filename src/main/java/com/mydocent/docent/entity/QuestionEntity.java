package com.mydocent.docent.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "llm_question")
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //추후 변경
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "llm_response_id")
    private LLMResponseEntity llmResponseEntity;

    @Column(length = 40)
    private String category;

    @Column(length = 255, nullable = false)
    private String fullQuestion;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    //생성자 추가
    public QuestionEntity(String fullQuestion, String category, LLMResponseEntity llmResponseEntity){
        this.fullQuestion = fullQuestion;
        this.category = category;
        this.llmResponseEntity = llmResponseEntity;
        this.createdAt = LocalDateTime.now();
    }
}
