package com.nhnacademy.nhnmart.admin.repo.impl;


import com.nhnacademy.nhnmart.admin.domain.Admin;

public interface AdminLoginRepo {

    boolean isUserPresent(String id);
    boolean validateUser(String id, String password);
    Admin getAdmin(String id);

}
