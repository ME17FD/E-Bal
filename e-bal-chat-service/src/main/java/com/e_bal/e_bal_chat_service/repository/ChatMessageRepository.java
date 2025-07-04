package com.e_bal.e_bal_chat_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.e_bal.e_bal_chat_service.models.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    java.util.List<ChatMessage> findByChat_Id(Long chatId);
    ChatMessage findLastMessage(Long chatId, Long recipientId);
}