package com.mydocent.docent.service;

import com.mydocent.docent.dto.LLMResponseDto;
import com.mydocent.docent.entity.LLMResponseEntity;
import com.mydocent.docent.entity.QuestionEntity;
import com.mydocent.docent.repository.LLMRepository;
import com.mydocent.docent.repository.QuestionRepository;
import com.mydocent.docent.util.LLMClient;
import com.mydocent.docent.util.LLMParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final LLMRepository llmRepository;
    private final LLMClient llmClient;
    private final LLMParser llmParser;

    public LLMResponseDto processQuestion(String question){
        // 1. LLM 호출
        String prompt = llmClient.buildPrompt(question);
        String response = llmClient.call(prompt);

        LLMResponseEntity responseEntity = llmParser.parse(response);
        llmRepository.save(responseEntity);

        // 2. 생성자로 질문 + 응답 연결
        QuestionEntity userQuestion = new QuestionEntity(question, responseEntity);
        questionRepository.save(userQuestion);

        // 3. DTO 반환
        return new LLMResponseDto(
                responseEntity.getId(),
                responseEntity.getArtistName(),
                responseEntity.getArtworkTitle(),
                responseEntity.getArtworkDescription(),
                responseEntity.getArtistDescription(),
                responseEntity.getArtworkBackground(),
                responseEntity.getAppreciationPoint(),
                responseEntity.getArtHistory(),
                responseEntity.getCreatedAt()
        );
    }
}
