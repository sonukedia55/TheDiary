package com.example.thediary;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String JSON_STRING;
    String json_string;
    EditText editDate, editSubject, editContent;


    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getJSON(savedInstanceState);
        //String view;
        getJSON(new View(this));
        editDate = (EditText) findViewById(R.id.editDate);
        editSubject = (EditText) findViewById(R.id.editSubject);
        editContent = (EditText) findViewById(R.id.editContent);


    }


    public void getJSON(View view){
        new BackgroundTask().execute();

    }
    public void sendJSON(View view){

        new StoreUserDataAsyncTask().execute();
    }


    class BackgroundTask extends AsyncTask<Void, Void, String>{
        String json_url;
        @Override
        protected void onPreExecute(){
            json_url = "https://kedia.000webhostapp.com/FetchDiaryDetails.php";
        }

        @Override
        protected String doInBackground(Void... voids) {
            try{
            URL url = new URL(json_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            while ((JSON_STRING = bufferedReader.readLine()) != null) {

                stringBuilder.append(JSON_STRING + "\n");
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return stringBuilder.toString().trim();
        } catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values){
            super.onProgressUpdate(values);
        }
        @Override
        protected void onPostExecute(String result){
            TextView textView = (TextView)findViewById(R.id.textview);
            //textView.setText(result);
            json_string = result;
        }


    }
    public void parseJSON(View view){

        if(json_string == null){


            Toast.makeText(getApplicationContext(),"Please Wait!!!",Toast.LENGTH_LONG).show();
            getJSON(view);
        }
        else{
            Intent intent = new Intent(this,DisplayListView.class);
            intent.putExtra("json_data",json_string);
            startActivity(intent);
        }
    }
    public  class StoreUserDataAsyncTask extends AsyncTask<Void, Void, Void>{


        String date =editDate.getText().toString();
        String subject =editSubject.getText().toString();
        String content = editContent.getText().toString();


        @Override
        protected Void doInBackground(Void... params) {




            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("date",date));
            dataToSend.add(new BasicNameValuePair("subject",subject));
            dataToSend.add(new BasicNameValuePair("content",content));

            HttpParams httpRequestParams = new BasicHttpParams();
            //HttpConnectionParams.setConnectionTimeout(httpRequestParams,CONNECTION_TIMEOUT);
            //HttpConnectionParams.setSoTimeout(httpRequestParams,CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost("https://kedia.000webhostapp.com/EnterInDiary.php");

            try{
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                client.execute(post);

            }catch (Exception e){
                e.printStackTrace();
            }

            startActivity(new Intent(MainActivity.this,MainActivity.class));

            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid){
            //progressDialog.dismiss();
            //userCallback.done(null);
            super.onPostExecute(aVoid);
        }

    }

}
