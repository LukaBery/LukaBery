package com.nhnacademy.nhnmart.user.repo.impl;

import com.nhnacademy.nhnmart.user.domain.User;

public interface UserLoginRepo {
    boolean isUserPresent(String id);
    boolean validateUser(String id, String password) throws Exception;
    void addUser(User user);
    User getUser(String id);
}
