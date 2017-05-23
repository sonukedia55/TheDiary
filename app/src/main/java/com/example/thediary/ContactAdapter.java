package com.example.thediary;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sonu Kedia on 21/05/2017.
 */
public class ContactAdapter extends ArrayAdapter{


    List list = new ArrayList();
    public ContactAdapter(Context context,int resource){
        super(context, resource);
    }


    public void add(Contacts object){
        super.add(object);
        list.add(object);
    }
    @Override
    public int getCount(){
        return list.size();
    }
    @Override
    public Object getItem(int position){
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row = convertView;
        final ContactHolder contactHolder;
        if(row == null){

            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout,parent,false);
            contactHolder = new ContactHolder();
            contactHolder.tx_date = (TextView)row.findViewById(R.id.tx_date);
            contactHolder.tx_subject = (TextView)row.findViewById(R.id.tx_subject);
            contactHolder.tx_content = (TextView)row.findViewById(R.id.tx_content);
            row.setTag(contactHolder);

        }
        else{
            contactHolder = (ContactHolder)row.getTag();

        }
        Contacts contacts = (Contacts)this.getItem(position);
        contactHolder.tx_date.setText(contacts.getDate());
        contactHolder.tx_subject.setText(contacts.getSubject());
        contactHolder.tx_content.setText(contacts.getContent());

        return row;
    }
    static class ContactHolder{
        TextView tx_date,tx_subject,tx_content;
    }


}
