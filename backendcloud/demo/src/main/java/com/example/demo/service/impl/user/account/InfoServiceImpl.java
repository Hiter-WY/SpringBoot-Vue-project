package com.example.demo.service.impl.user.account;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.service.impl.utils.UserDetailsImpl;
import com.example.demo.service.user.account.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class InfoServiceImpl implements InfoService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Map<String, String> getinfo() {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        User user = loginUser.getUser();

        Map<String, String> map = new HashMap<>();
        map.put("error_message", "success");
        map.put("id", user.getId().toString());
        map.put("username", user.getUsername());
        map.put("photo", user.getPhoto());
        map.put("rating", user.getRating().toString());
        map.put("email",user.getEmail());
        return map;
    }
    @Override
    public boolean updatePhoto(Integer userId, String photoUrl) {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        User user = loginUser.getUser();

        if (user != null && user.getId().equals(userId)) {
            user.setPhoto(photoUrl);
            userMapper.updateById(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean updatePassword(Integer userId, String oldPassword, String newPassword) {
        if (userId == null || oldPassword == null || oldPassword.isEmpty() || newPassword == null || newPassword.isEmpty()) {
            throw new IllegalArgumentException("Invalid input data");
        }
        System.out.println("updatePassword - userId: " + userId + ", oldPassword: " + oldPassword + ", newPassword: " + newPassword); // 添加日志
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        User user = loginUser.getUser();

        if (user != null && user.getId().equals(userId) && passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userMapper.updateById(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateEmail(Integer userId, String newEmail) {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        User user = loginUser.getUser();

        String emailRegex = "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6})*$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(newEmail);
        if (!matcher.matches()) {
            return false;
        }

        if (user != null && user.getId().equals(userId)) {
            user.setEmail(newEmail);
            userMapper.updateById(user);
            return true;
        }
        return false;
    }

}

