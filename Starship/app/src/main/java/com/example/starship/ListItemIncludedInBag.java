package com.example.starship;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListItemIncludedInBag extends MainActivity {
    ListView ListItemBag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item_included_in_bag);
        String label_list = "Прибыль " + getIntent().getStringExtra("label");
        setTitle(label_list);
        ListItemBag = (ListView)findViewById(R.id.ListItemBag);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.listview_activity, help_array);
        ListItemBag.setAdapter(arrayAdapter);
    }
}
