package com.kharchenko.spring.service;

import com.kharchenko.spring.entity.Message;
import com.kharchenko.spring.repository.DAOMessage;
import com.kharchenko.spring.repository.DAOUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServiceMessageImpl implements ServiceMessage{
    @Autowired
    private DAOMessage dao;

    @Override
    @Transactional
    public List<Message> getMessagesByUsername(String username) {
        return dao.getMessagesByUsername(username);
    }

    @Override
    @Transactional
    public void saveMessage(Message message) {
        dao.saveMessage(message);
    }
}
