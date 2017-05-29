package com.example.thediary;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DisplayListView extends AppCompatActivity {

    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    ContactAdapter contactAdapter;
    ListView listView;
    Context c;
    JSONObject JO;

    ArrayList<String> date,subject,content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list_view);
        listView = (ListView)findViewById(R.id.listview);

        date = new ArrayList();
        subject = new ArrayList();
        content = new ArrayList();

        contactAdapter = new ContactAdapter(this,R.layout.row_layout);
        listView.setAdapter(contactAdapter);


        json_string = getIntent().getExtras().getString("json_data");
        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("response");


            int count = 0;
            while (count<jsonArray.length()){
                JO = jsonArray.getJSONObject(count);
                date.add(JO.getString("date"));
                subject.add(JO.getString("subject"));
                content.add(JO.getString("content"));
                Contacts contacts = new Contacts(date.get(count),subject.get(count),content.get(count));
                contactAdapter.add(contacts);


                count++;
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                //Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(DisplayListView.this, ToExpend.class);


                // Pass all data rank
                //Toast.makeText(getApplicationContext(),"jk"+position,Toast.LENGTH_LONG).show();

                i.putExtra("date",date.get(position));
                i.putExtra("subject",subject.get(position));
                i.putExtra("content",content.get(position));
                 //Open SingleItemView.java Activity
                startActivity(i);
                //Toast.makeText(getApplicationContext(),"Please Wait!!!",Toast.LENGTH_LONG).show();
            }

        });




    }




}
