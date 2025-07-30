package com.mydocent.docent.repository;

import com.mydocent.docent.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
}
