package com.e_bal.e_bal_chat_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatResponseDTO {
    private Long chatId;
    private Long senderId;
    private Long recipientId;
    private String lastMessage;
} 