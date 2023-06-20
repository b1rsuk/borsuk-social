package com.example.borsuksocial.controller;

import com.example.borsuksocial.model.Message;
import com.example.borsuksocial.model.MessageDto;
import com.example.borsuksocial.model.SimpleMessageDto;
import com.example.borsuksocial.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final MessageService messageService;

    @MessageMapping("/private-message")
    public void privateMessage(@Payload SimpleMessageDto simpleMessageDto) {
        Message message = messageService.convertToMessage(simpleMessageDto);
        messageService.save(message);

        simpMessagingTemplate.convertAndSendToUser(String.valueOf(simpleMessageDto.getUserId()), "/private", simpleMessageDto);
    }

    @GetMapping("/client/{clientId}")
    public Page<MessageDto> findChatHistoryByClientId(@PathVariable Long clientId,
                                                      @RequestParam(defaultValue = "0") Integer pageNo,
                                                      @RequestParam(defaultValue = "id") String sortBy,
                                                      @RequestParam(defaultValue = "DESC") Direction direction) {
        Pageable paging =  PageRequest.of(pageNo, 25, Sort.by(direction, sortBy));

        return messageService.findMessageDtoByClientId(paging, clientId);
    }

}
