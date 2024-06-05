package com.example.demo.service.impl.user.bot;


import com.example.demo.mapper.BotMapper;
import com.example.demo.pojo.Bot;
import com.example.demo.pojo.User;
import com.example.demo.service.impl.utils.UserDetailsImpl;
import com.example.demo.service.user.bot.RemoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

@Service
public class RemoveServiceImpl implements RemoveService {
    @Autowired
    private BotMapper botMapper;
    @Override
    public Map<String, String> remove(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();
        int bot_id = Integer.parseInt(data.get("bot_id"));
        Bot bot = botMapper.selectById(bot_id);

        Map<String, String> map = new HashMap<>();

        if(bot == null){
            map.put("error_message", "BOT不存在或已被删除");
            return map;
        }

        if(!Objects.equals(bot.getUserId(), user.getId())){
            map.put("error_message","没有权限删除该BOT");
            return map;
        }

        botMapper.deleteById(bot_id);

        map.put("error_message","success");

        return map;
    }
}
