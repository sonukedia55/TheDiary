package com.example.thediary;

/**
 * Created by Sonu Kedia on 21/05/2017.
 */
public class Contacts {
    private String date,subject,content;

    public Contacts(String date,String subject,String content){
        //this.setContent(content);
        this.setDate(date);
        this.setSubject(subject);
        this.setContent(content);
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
