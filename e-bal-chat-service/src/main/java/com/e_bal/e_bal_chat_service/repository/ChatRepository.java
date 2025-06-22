package com.e_bal.e_bal_chat_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.e_bal.e_bal_chat_service.models.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    Chat findByUser1AndUser2(Long user1, Long user2);
} 