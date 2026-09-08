package com.kharchenko.spring.service;

import com.kharchenko.spring.entity.Message;

import java.util.List;

public interface ServiceMessage {
    public List<Message> getMessagesByUsername(String username);

    public void saveMessage(Message message);
}
