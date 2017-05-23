package com.example.thediary;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Sonu Kedia on 23/05/2017.
 */
public class Rows extends AppCompatActivity {

    Context c;
    TextView editDate, editSubject, editContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.row_layout);



    }



    public void openDetails(View view){

            String date = editDate.getText().toString();
            String subject = editSubject.getText().toString();
            String content = editContent.getText().toString();


            openDetailActivity(date,subject,content);

    }

    private void openDetailActivity(String date,String subject,String content){



        Intent i = new Intent(c,ToExpend.class);

        //i.putExtra("date",date);
        //i.putExtra("subject",subject);
        //i.putExtra("content",content);
        c.startActivity(i);
    }
}
