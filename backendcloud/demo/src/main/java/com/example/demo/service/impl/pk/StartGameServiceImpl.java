package com.example.demo.service.impl.pk;


import com.example.demo.consumer.WebSocketServer;
import com.example.demo.service.pk.StartGameService;
import org.springframework.stereotype.Service;

@Service
public class StartGameServiceImpl implements StartGameService {
    @Override
    public String startGame(Integer aId , Integer aBotId ,Integer bId, Integer bBotId) {
        System.out.println("start game: " + aId +" " + bId);
        WebSocketServer.startGame(aId, aBotId, bId, bBotId);
        return "start game success";
    }
}
