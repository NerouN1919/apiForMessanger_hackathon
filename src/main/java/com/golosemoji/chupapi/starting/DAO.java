package com.golosemoji.chupapi.starting;

import com.golosemoji.chupapi.dataTable.Message;
import com.golosemoji.chupapi.dataTable.MessageAudio;
import com.golosemoji.chupapi.dataTable.User;
import com.golosemoji.chupapi.exceptions.NoSuchIn;
import com.golosemoji.chupapi.returns.GetMes;
import com.golosemoji.chupapi.returns.Login;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class DAO {
    @Autowired
    private EntityManager entityManager;
    public User registration(User user){
        Session session = entityManager.unwrap(Session.class);
        if(isIn(user)){
            throw new NoSuchIn("already have this login");
        }
        session.save(user);
        return user;
    }
    public boolean isIn(User users){
        Session session = entityManager.unwrap(Session.class);
        List<User> list = session.createQuery("from User where login = '" + users.getLogin() + "'", User.class).getResultList();
        if(list.size() != 0){
            return true;
        }
        return false;
    }
    public Login login(User user){
        Session session = entityManager.unwrap(Session.class);
        if(!isIn(user)){
            return new Login();
        }
        List<User> list = session.createQuery("from  User where login = '" + user.getLogin() + "'").getResultList();
        if(list.get(0).getPassword().equals(user.getPassword())){
            return new Login(list.get(0).getId());
        } else {
            return new Login();
        }
    }
    public Message sendMessage(Message message){
        Session session = entityManager.unwrap(Session.class);
        message.setDate(new Date());
        User userFrom = session.get(User.class, message.getUserFrom().getId());
        User userTo = session.get(User.class, message.getUserTo().getId());
        userFrom.addMessageFrom(message);
        userTo.addMessageTo(message);
        session.update(userFrom);
        session.update(userTo);
        return message;
    }
    public List<Message> getMessage(GetMes getMes){
        Session session = entityManager.unwrap(Session.class);
        List<Message> messageList = session.createQuery("from Message where to_user = '" + getMes.getTo() + "' and from_user = '" + getMes.getFrom() + "'").getResultList();
        List<Message> messageListSec = session.createQuery("from Message where to_user = '" + getMes.getFrom() + "' and from_user = '" + getMes.getTo() + "'").getResultList();
        List<Message> endList = new ArrayList<>();
        endList.addAll(messageList);
        endList.addAll(messageListSec);
        return endList;
    }
    public MessageAudio sendAudio(MessageAudio messageAudio){
        Session session = entityManager.unwrap(Session.class);
        messageAudio.setDate(new Date());
        User userFrom = session.get(User.class, messageAudio.getUserFrom().getId());
        User userTo = session.get(User.class, messageAudio.getUserTo().getId());
        userFrom.addMessageFrom(messageAudio);
        userTo.addMessageTo(messageAudio);
        session.update(userFrom);
        session.update(userTo);
        return messageAudio;
    }
    public List<MessageAudio> getAudioMessage(GetMes getMes){
        Session session = entityManager.unwrap(Session.class);
        List<MessageAudio> messageList = session.createQuery("from MessageAudio where to_user = '" + getMes.getTo() + "' and from_user = '" + getMes.getFrom() + "'").getResultList();
        List<MessageAudio> messageListSec = session.createQuery("from MessageAudio where to_user = '" + getMes.getFrom() + "' and from_user = '" + getMes.getTo() + "'").getResultList();
        List<MessageAudio> endList = new ArrayList<>();
        endList.addAll(messageList);
        endList.addAll(messageListSec);
        return endList;
    }
}
