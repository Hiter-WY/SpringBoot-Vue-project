package com.example.demo.controller.user.account;

import com.example.demo.service.user.account.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
@RestController
public class InfoController {
    @Autowired
    private InfoService infoService;

    @GetMapping("/user/account/info/")
    public Map<String, String> getinfo() {
        return infoService.getinfo();
    }
    @PostMapping("/user/account/update/photo/")
    public boolean updatePhoto(@RequestParam Integer userId, @RequestParam String photoUrl) {
        return infoService.updatePhoto(userId, photoUrl);
    }

    @PostMapping("/user/account/update/password/")
    public boolean updatePassword(@RequestParam Integer userId, @RequestParam String oldPassword, @RequestParam String newPassword) {
        return infoService.updatePassword(userId, oldPassword, newPassword);
    }

    @PostMapping("/user/account/update/email/")
    public  boolean updateEmail(@RequestParam Integer userId, @RequestParam String newEmail){
        return infoService.updateEmail(userId, newEmail);
    }

}

