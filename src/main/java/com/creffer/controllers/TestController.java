package com.creffer.controllers;

import com.creffer.dto.ResponseDTO;
import com.creffer.models.RequestTable;
import com.creffer.repository.RequestTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Random;

@Controller
public class TestController {

    @Autowired
    private RequestTableRepository requestTableRepository;

    private static int gameNumber;

    static {
        Random random = new Random();
        gameNumber = random.nextInt(10) +1;
    }

    @RequestMapping(value="/doGame",method = RequestMethod.GET)
    @ResponseBody
    public ResponseDTO play(@RequestParam int number){
        RequestTable requestTable = new RequestTable();
        requestTable.setGuess(number);
        requestTable.setRealNum(gameNumber);
        requestTableRepository.save(requestTable);
        return new ResponseDTO(number==gameNumber, LocalDateTime.now());
    }
    private void test(){
        System.out.println("I'm here");
    }

    private String stringTest(){
        return "I'm here";
    }

    protected int intTest(String question){
        return 42;
    }
}
