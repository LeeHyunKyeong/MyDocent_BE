package com.mydocent.docent.controller;

import com.mydocent.docent.dto.LLMResponseDto;
import com.mydocent.docent.dto.QuestionRequestDto;
import com.mydocent.docent.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping
    public ResponseEntity<LLMResponseDto> askQuestion(@RequestBody QuestionRequestDto questionRequestDto){
        LLMResponseDto response = questionService.processQuestion(questionRequestDto.question());
        return ResponseEntity.ok(response);
    }
}
