package com.e_bal.e_bal_chat_service.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDTO {
    private Long chatId;
    private Long senderId;
    private Long recipientId;
    private String content;
    private LocalDateTime timestamp;
} 