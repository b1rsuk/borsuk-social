package com.example.borsuksocial.service;

import com.example.borsuksocial.exception.UserNotFoundException;
import com.example.borsuksocial.model.Message;
import com.example.borsuksocial.model.MessageDto;
import com.example.borsuksocial.model.SimpleMessageDto;
import com.example.borsuksocial.repository.ClientRepository;
import com.example.borsuksocial.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MessageService {

    private final ClientRepository clientRepository;
    private final MessageRepository messageRepository;

    public Message save(Message message) {
        return messageRepository.save(message);
    }

    public Page<MessageDto> findMessageDtoByClientId(Pageable pageable, Long clientId) {
        return messageRepository.findMessageDtoByClientId(pageable, clientId);
    }

    public Message convertToMessage(SimpleMessageDto simpleMessageDto) {
        Message message = new Message();
        message.setClient(clientRepository.findById(simpleMessageDto.getUserId()).orElseThrow(() -> new UserNotFoundException(simpleMessageDto.getUserId())));
        message.setReceipter(clientRepository.findById(simpleMessageDto.getReceipterId()).orElseThrow(() -> new UserNotFoundException(simpleMessageDto.getUserId())));
        message.setMessage(simpleMessageDto.getMessage());

        return message;
    }
}
