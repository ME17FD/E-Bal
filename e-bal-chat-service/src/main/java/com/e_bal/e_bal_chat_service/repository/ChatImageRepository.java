package com.e_bal.e_bal_chat_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.e_bal.e_bal_chat_service.models.ChatImage;

public interface ChatImageRepository extends JpaRepository<ChatImage, Long> {
} 