package com.example.demo.service.user.account;


import java.util.Map;

public interface InfoService {
    public Map<String, String> getinfo();
    boolean updatePhoto(Integer userId, String photoUrl);
    boolean updatePassword(Integer userId, String oldPassword, String newPassword);

    boolean updateEmail(Integer userId, String newEmail);
}
