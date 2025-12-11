package com.example.demo.model;
public class Greeting {
    private long id;
    private String content;
    private String timestamp;

    public Greeting() {}
    public Greeting(long id, String content, String timestamp) {
        this.id = id; this.content = content; this.timestamp = timestamp;
    }
    public long getId(){ return id; }
    public void setId(long id){ this.id = id; }
    public String getContent(){ return content; }
    public void setContent(String content){ this.content = content; }
    public String getTimestamp(){ return timestamp; }
    public void setTimestamp(String timestamp){ this.timestamp = timestamp; }
}