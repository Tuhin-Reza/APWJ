package com.service;

import com.domain.User;

import java.util.List;

public interface UserService {

    public List<User> list();
    public User get(Long user_id);
    public User create(User user);
    public  User update(User user);

    public  void delete(Long user_id);
}
