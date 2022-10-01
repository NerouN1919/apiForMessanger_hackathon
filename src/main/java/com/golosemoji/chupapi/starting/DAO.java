package com.golosemoji.chupapi.starting;

import com.golosemoji.chupapi.dataTable.Message;
import com.golosemoji.chupapi.dataTable.User;
import com.golosemoji.chupapi.exceptions.Info;
import com.golosemoji.chupapi.exceptions.NoSuchIn;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Repository
public class DAO {
    @Autowired
    private EntityManager entityManager;

    public void test(){
        Session session = entityManager.unwrap(Session.class);
        User user = new User("aboba", "login", "parol", "ya aboba");
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        Message message = new Message("privet", sqlDate);
        user.addMessageTo(message);
        session.save(user);
    }
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


}
