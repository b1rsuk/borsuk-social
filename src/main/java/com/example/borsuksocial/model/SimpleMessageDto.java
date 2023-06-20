package com.example.borsuksocial.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class SimpleMessageDto {
    Long userId;
    Long receipterId;

    String message;
}
