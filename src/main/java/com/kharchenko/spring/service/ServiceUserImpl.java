package com.kharchenko.spring.service;

import com.kharchenko.spring.entity.Authorities;
import com.kharchenko.spring.entity.User;
import com.kharchenko.spring.repository.DAOUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServiceUserImpl implements ServiceUser{
    @Autowired
    private DAOUser dao;

    @Override
    @Transactional
    public void saveUser(User user) {
        dao.saveUser(user);
    }

    @Override
    @Transactional
    public User getUser(int id) {
        return dao.getUser(id);
    }

    @Override
    @Transactional
    public List<User> getAlUsers() {
        return dao.getAlUsers();
    }

    @Override
    @Transactional
    public User getUserByUsername(String username) {
        return dao.getUserByUsername(username);
    }

    @Override
    @Transactional
    public void deleteUser(String username) {
        dao.deleteUser(username);
    }

    @Override
    @Transactional
    public void saveAuthorities(Authorities authorities) {
        dao.saveAuthorities(authorities);
    }

    @Override
    @Transactional
    public void deleteAuthorities(String username) {
        dao.deleteAuthorities(username);
    }
}
