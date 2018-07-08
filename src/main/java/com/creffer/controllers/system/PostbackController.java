package com.creffer.controllers.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostbackController {
    @GetMapping(value = "/postback", params = {""})
    @ResponseBody
    public void postback(){

    }
}
