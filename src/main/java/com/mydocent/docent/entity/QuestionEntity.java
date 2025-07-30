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

    @Column
    private String fullQuestion;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    //생성자 추가
    public QuestionEntity(String fullQuestion, LLMResponseEntity llmResponseEntity){
        this.fullQuestion = fullQuestion;
        this.llmResponseEntity = llmResponseEntity;
        this.createdAt = LocalDateTime.now();
    }
}
