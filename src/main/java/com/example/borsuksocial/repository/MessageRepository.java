package com.example.borsuksocial.repository;

import com.example.borsuksocial.model.Message;
import com.example.borsuksocial.model.MessageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("SELECT new com.example.borsuksocial.model.MessageDto(m.id, c.id, r.id, m.message) " +
            "FROM Message m " +
            "INNER JOIN m.client c " +
            "INNER JOIN m.receipter r " +
            "WHERE c.id = :clientId OR r.id = :clientId")
    Page<MessageDto> findMessageDtoByClientId(Pageable pageable,  @Param("clientId") Long clientId);
}
