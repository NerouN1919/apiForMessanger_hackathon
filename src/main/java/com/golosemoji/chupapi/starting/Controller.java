package com.golosemoji.chupapi.starting;

import com.golosemoji.chupapi.dataTable.Message;
import com.golosemoji.chupapi.dataTable.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;
import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {
    @Autowired
    private Service service;
    @RequestMapping("/test")
    public void test(){
        service.test();
    }
    @PostMapping("/reg")
    public User registration(@RequestBody User user){
        return service.registration(user);
    }
    @PostMapping("/log")
    public Login login(@RequestBody User user){
        return service.login(user);
    }
    @PostMapping("/send")
    public Message sendMessage(@RequestBody Message message){
        return service.sendMessage(message);
    }
    @PostMapping("get")
    public List<Message> getMessage(@RequestBody GetMes getMes){
        return service.getMessage(getMes);
    }
}
