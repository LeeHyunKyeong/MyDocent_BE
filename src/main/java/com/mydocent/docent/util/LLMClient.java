package com.mydocent.docent.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class LLMClient {
    private final RestClient restClient;

    public LLMClient(@Value("${perplexity.api-key}") String apiKey) {
        this.restClient = RestClient.builder()
                .baseUrl("https://api.perplexity.ai")
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .build();
    }

    public String buildPrompt(String userQuestion) {
        return """
            {
              "model": "sonar-pro",
              "messages": [
                {
                  "role": "system",
                  "content": "너는 예술 작품 안내 도슨트야. 무조건 한국어로만 답변하고, 문장 끝은 “-습니다.”로 공손한 어투로 답변해줘. 작가 이름과 작품명은 항상 표준화된 한글 명칭으로만 표기해줘. 사용자가 작가 이름과 작품명을 제공하면 아래 형식으로 답변해:\\n작가:\\n작품명:\\n작품 소개:\\n작가 소개:\\n작품 배경:\\n감상 포인트:\\n미술사적 맥락:\\n 감상 포인트는 '첫째, 둘째, 셋째'와 같은 번호 형식으로 구분해서 작성해주고, 엔터를 생략하고 한 문단으로 이어줘. 답변에 [2]와 같은 출처 표시와 **와 같은 강조 표시는 모두 제거해줘."
                },
                {
                  "role": "user",
                  "content": "%s"
                }
              ]
            }
        """.formatted(userQuestion);
    }

    public String call(String promptJson) {
        try {
            return restClient.post()
                    .uri("/chat/completions")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(promptJson)
                    .retrieve()
                    .body(String.class); // 응답 바디만 가져옴
        } catch (Exception e) {
            System.err.println("❌ LLM 호출 실패: " + e.getMessage());
            throw new RuntimeException("LLM API 호출 실패", e);
        }
    }
}
