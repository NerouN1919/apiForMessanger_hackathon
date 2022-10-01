package com.golosemoji.chupapi.starting;

import com.golosemoji.chupapi.dataTable.Message;
import com.golosemoji.chupapi.dataTable.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Negative;
import java.lang.reflect.Member;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    private DAO dao;
    @Transactional
    public void test(){
        dao.test();
    }
    @Transactional
    public User registration(User user){
        return dao.registration(user);
    }
    @Transactional
    public Login login(User user){
        return dao.login(user);
    }
    @Transactional
    public Message sendMessage(Message message){
        return dao.sendMessage(message);
    }
    @Transactional
    public List<Message> getMessage(GetMes getMes){
        return dao.getMessage(getMes);
    }
}
