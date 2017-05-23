package com.example.thediary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ToExpend extends AppCompatActivity {

    TextView textdate,textsubject,textcontent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expend);

        textdate = (TextView)findViewById(R.id.textDate);
        textsubject = (TextView)findViewById(R.id.textSubject);
        textcontent = (TextView)findViewById(R.id.textContent);

       // Intent i = getIntent();
        //String date=i.getExtras().getString("date");
        //String subject=i.getExtras().getString("subject");
        //String content=i.getExtras().getString("content");

        //textdate.setText(date);
        //textcontent.setText(subject);
        //textcontent.setText(content);


    }
}
