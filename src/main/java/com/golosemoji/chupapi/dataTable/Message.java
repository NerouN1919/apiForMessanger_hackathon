package com.golosemoji.chupapi.dataTable;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "content")
    private String content;
    @Column(name = "date_public")
    private java.sql.Timestamp date;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "from_user")
    private User userFrom;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "to_user")
    private User userTo;

    public Message() {
    }

    public Message(String content, java.util.Date date) {
        this.content = content;
        this.date = new java.sql.Timestamp(date.getTime());
    }

    public Message(String content) {
        this.content = content;
    }

    public Message(String content, Date date, User userFrom, User userTo) {
        this.content = content;
        this.date = new java.sql.Timestamp(date.getTime());
        this.userFrom = userFrom;
        this.userTo = userTo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public java.sql.Timestamp getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = new java.sql.Timestamp(date.getTime());
    }

    public User getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(User userFrom) {
        this.userFrom = userFrom;
    }

    public User getUserTo() {
        return userTo;
    }

    public void setUserTo(User userTo) {
        this.userTo = userTo;
    }
}
