package com.example.borsuksocial.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class MessageDto extends SimpleMessageDto {
    Long id;

    public MessageDto(Long id, Long userId, Long receipterId, String message) {
        super(userId, receipterId, message);
        this.id = id;
    }

}
