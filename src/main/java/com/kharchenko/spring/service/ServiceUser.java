package com.kharchenko.spring.service;

import com.kharchenko.spring.entity.Authorities;
import com.kharchenko.spring.entity.User;

import java.util.List;

public interface ServiceUser {
    public void saveUser(User user);

    public User getUser(int id);

    public List<User> getAlUsers();

    public User getUserByUsername(String username);

    public void deleteUser(String username);

    public void saveAuthorities(Authorities authorities);

    public void deleteAuthorities(String username);
}
