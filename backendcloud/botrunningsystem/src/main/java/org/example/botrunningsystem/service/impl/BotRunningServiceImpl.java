package org.example.botrunningsystem.service.impl;

import org.example.botrunningsystem.service.BotRunningService;
import org.example.botrunningsystem.service.impl.utlis.BotPool;
import org.springframework.stereotype.Service;

@Service
public class BotRunningServiceImpl implements BotRunningService {
    public static final BotPool botPool = new BotPool();
    @Override
    public String addBot(Integer userId, String botCode, String input) {
        System.out.println("add bot" + userId +" " + botCode + " " + input);
        botPool.addBot(userId, botCode, input);
        return "add bot success";
    }
}
