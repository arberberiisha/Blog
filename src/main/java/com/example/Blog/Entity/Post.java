package com.example.Blog.Entity;

import javax.persistence.*;


@Entity
@Table(name = "posts")
public class Post {

    public Post(String title, String ds, User user) {
        this.title = title;
        this.ds = ds;
        this.user = user;
    }

    public Post() {

    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String ds;

    @ManyToOne()
    @JoinColumn(name="user", nullable=false, insertable=true,updatable=true)
    private User user;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDs(){
        return ds;
    }

    public void setDs(String ds) {
        this.ds = ds;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public Post(String a, User b) {
//
//        this.a = a;
//        this.b = b;
//    }
//
//    public long getId() {
//        return tID;
//    }
//
//    public void setId(long id) {
//        this.tID = id;
//    }
//
//    public String getA() {
//        return a;
//    }
//
//    public void setA(String a) {
//        this.a = a;
//    }
//
//    public User getB() {
//        return b;
//    }
//
//    public void setB(User b) {
//        this.b = b;
//    }
//
//    public String getC() {
//        return c;
//    }
//
//    public void setC(String c) {
//        this.c = c;
//    }
}
