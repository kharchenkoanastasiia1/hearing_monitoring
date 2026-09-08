package com.kharchenko.spring.repository;

import com.kharchenko.spring.entity.Doctor;
import com.kharchenko.spring.entity.Message;

import java.util.List;

public interface DAOMessage {
    public List<Message> getMessagesByUsername(String username);

    public void saveMessage(Message message);
}
