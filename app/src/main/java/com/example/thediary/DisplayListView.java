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

public class DisplayListView extends AppCompatActivity {

    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    ContactAdapter contactAdapter;
    ListView listView;
    Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list_view);
        listView = (ListView)findViewById(R.id.listview);


        contactAdapter = new ContactAdapter(this,R.layout.row_layout);
        listView.setAdapter(contactAdapter);


        json_string = getIntent().getExtras().getString("json_data");
        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("response");


            int count = 0;
            String date,subject,content;
            while (count<jsonArray.length()){
                JSONObject JO = jsonArray.getJSONObject(count);
                date = JO.getString("date");
                subject = JO.getString("subject");
                content = JO.getString("content");
                Contacts contacts = new Contacts(date,subject,content);
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

                //i.putExtra("date",basket);
                //i.putExtra("subject",subject);
                //i.putExtra("content",content);
                // Open SingleItemView.java Activity
                startActivity(i);
                //Toast.makeText(getApplicationContext(),"Please Wait!!!",Toast.LENGTH_LONG).show();
            }

        });




    }




}
