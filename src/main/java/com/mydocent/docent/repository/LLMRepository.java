package com.mydocent.docent.repository;

import com.mydocent.docent.entity.LLMResponseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LLMRepository extends JpaRepository<LLMResponseEntity, Long> {
}
