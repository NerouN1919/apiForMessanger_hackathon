package com.golosemoji.chupapi.dataTable;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "messageaudio")
public class MessageAudio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "path")
    private String path;
    @Column(name = "date_public")
    private java.sql.Timestamp date;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "from_user")
    private User userFrom;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "to_user")
    private User userTo;

    public MessageAudio() {
    }

    public MessageAudio(String content, java.util.Date date) {
        this.path = content;
        this.date = new java.sql.Timestamp(date.getTime());
    }

    public MessageAudio(String content) {
        this.path = content;
    }

    public MessageAudio(String content, Date date, User userFrom, User userTo) {
        this.path = content;
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

    public String getPath() {
        return path;
    }

    public void setPath(String content) {
        this.path = content;
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
