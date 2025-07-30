package com.mydocent.docent.util;

import com.mydocent.docent.entity.LLMResponseEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class LLMParser {
    public LLMResponseEntity parse(String llmRawJson) {
        try {
            JSONObject root = new JSONObject(llmRawJson);

            JSONArray citationsArray = root.optJSONArray("citations");
            String citations = null;
            if (citationsArray != null) {
                citations = citationsArray.toString();
            }

            JSONArray choices = root.optJSONArray("choices");
            if (choices == null || choices.length() == 0) {
                throw new RuntimeException("LLM 응답에 choices가 없습니다.");
            }

            JSONObject message = choices.getJSONObject(0).optJSONObject("message");
            if (message == null || !message.has("content")) {
                throw new RuntimeException("message.content 항목이 없습니다.");
            }

            String content = message.getString("content");

            return extractFromContent(content, citations);

        } catch (JSONException e) {
            System.err.println("JSON 파싱 실패: " + e.getMessage());
            throw new RuntimeException("잘못된 JSON 응답입니다", e);
        }
    }

    private LLMResponseEntity extractFromContent(String content, String citations) {
        return new LLMResponseEntity(
                extract(content, "작가:", "작품명:"),
                extract(content, "작품명:", "작품 소개:"),
                extract(content, "작품 소개:", "작가 소개:"),
                extract(content, "작가 소개:", "작품 배경:"),
                extract(content, "작품 배경:", "감상 포인트:"),
                extract(content, "감상 포인트:", "미술사적 맥락:"),
                extract(content, "미술사적 맥락:", null),
                citations
        );
    }

    private String extract(String text, String start, String end) {
        int startIndex = text.indexOf(start) + start.length();
        int endIndex = (end != null) ? text.indexOf(end) : text.length();
        return text.substring(startIndex, endIndex).trim();
    }
}
