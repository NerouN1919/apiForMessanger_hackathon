package com.golosemoji.chupapi.starting;

import com.golosemoji.chupapi.dataTable.Message;
import com.golosemoji.chupapi.dataTable.MessageAudio;
import com.golosemoji.chupapi.dataTable.User;
import com.golosemoji.chupapi.returns.GetMes;
import com.golosemoji.chupapi.returns.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    private DAO dao;
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
    @Transactional
    public MessageAudio sendAudio(MessageAudio messageAudio){
        return dao.sendAudio(messageAudio);
    }
    @Transactional
    public List<MessageAudio> getAudioMessage(GetMes getMes){
        return dao.getAudioMessage(getMes);
    }
}
