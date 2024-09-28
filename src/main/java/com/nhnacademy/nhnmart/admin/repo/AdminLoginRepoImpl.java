package com.nhnacademy.nhnmart.admin.repo;

import com.nhnacademy.nhnmart.admin.domain.Admin;
import com.nhnacademy.nhnmart.admin.exception.AdminNotExistException;
import com.nhnacademy.nhnmart.admin.repo.impl.AdminLoginRepo;
import com.nhnacademy.nhnmart.user.domain.User;
import org.springframework.stereotype.Repository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Repository
public class AdminLoginRepoImpl implements AdminLoginRepo {

    private Properties properties = new Properties();
    private static final String CS_USER_FILEPATH = "src/main/resources/csUserList.properties";


    @Override
    public boolean isUserPresent(String id) {
        try (FileInputStream in = new FileInputStream(CS_USER_FILEPATH)) {
            properties.load(in);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return properties.containsKey(id);
    }

    @Override
    public boolean validateUser(String id, String password) {

        if (!isUserPresent(id)) {
            throw new AdminNotExistException(id);
        }

        String userData = properties.getProperty(id);
        String[] parts = userData.split(",");

        return parts[0].equals(password);
    }

    @Override
    public Admin getAdmin(String id) {
        String userData = properties.getProperty(id);
        String[] parts = userData.split(",");
        Admin admin = new Admin(id, parts[0], parts[1]);
        return admin;
    }

}
