package com.golosemoji.chupapi.dataTable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "status")
    private String status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userFrom")
    private List<Message> messagesFrom;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userTo")
    private List<Message> messagesTo;
    public void addMessageFrom(Message message){
        if(messagesFrom == null){
            messagesFrom = new ArrayList<>();
        }
        messagesFrom.add(message);
        message.setUserFrom(this);
    }
    public void addMessageTo(Message message){
        if(messagesTo == null){
            messagesTo = new ArrayList<>();
        }
        messagesTo.add(message);
        message.setUserTo(this);
    }
    public User() {
    }

    public User(String name, String login, String password, String status) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
