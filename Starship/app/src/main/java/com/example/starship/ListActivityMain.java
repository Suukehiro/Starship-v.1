package com.example.starship;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListActivityMain extends MainActivity {
    public ListView Mainlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_main);
        Mainlist = (ListView)findViewById(R.id.ListItemBag);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.listview_activity, arr_name);
        Mainlist.setAdapter(arrayAdapter);


    }
}
