package com.nhnacademy.nhnmart.user.repo;

import com.nhnacademy.nhnmart.admin.domain.Admin;
import com.nhnacademy.nhnmart.user.domain.User;
import com.nhnacademy.nhnmart.user.exception.UserNotFoundException;
import com.nhnacademy.nhnmart.user.exception.UserValidationFailException;
import com.nhnacademy.nhnmart.user.repo.impl.UserLoginRepo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserLoginRepoImpl implements UserLoginRepo {

    private Map<String, User> userMap = new HashMap<>();


    @Override
    public boolean isUserPresent(String id) {
        if(userMap.containsKey(id)) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean validateUser(String id, String password) {
        if(!isUserPresent(id)){
            throw new UserNotFoundException(id);
        }
        if(!password.equals(userMap.get(id).getPassword())){
            throw new UserValidationFailException();
        }else {
            return true;
        }
    }

    @Override
    public void addUser(User user) {
        userMap.put(user.getId(), user);
    }
    @Override
    public User getUser(String id) {
        return userMap.get(id);
    }
}
