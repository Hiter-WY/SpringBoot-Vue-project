package com.example.demo.controller.pk;


import com.example.demo.service.pk.StartGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class StartGameController {
    @Autowired
    private StartGameService startGameService;
    @PostMapping("/pk/start/game/")
    public String startGame(@RequestParam MultiValueMap<String, String> data){
        Integer aId = Integer.parseInt(Objects.requireNonNull(data.getFirst("a_id")));
        Integer bId = Integer.parseInt(Objects.requireNonNull(data.getFirst("b_id")));
        System.out.println("a_id: " + aId + "\n" + "b_id: " + bId);
        return startGameService.startGame(aId, bId);
    }
}
