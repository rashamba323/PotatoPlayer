package com.potato.potatoplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    final String ATRIBUTE_NAME_TEXT = "text";
    final String ATRIBUTE_NAME_IMAGE = "image";

    String[] someNameSong = {"someNameSong1","someNameSong2","someNameSong3","someNameSong4","someNameSong5"};
    String[] someNameGroup = {"someNameGroup1","someNameGroup2","someNameGroup3","someNameGroup4","someNameGroup5"};
    int imageAlbom = R.drawable.ic_launcher_foreground;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Map<String,Object>> data = new ArrayList<Map<String, Object>>(someNameGroup.length);
        Map<String, Object> m;
        for (int i = 0; i< someNameGroup.length; i++){
            m = new HashMap<String, Object>();
            m.put(ATRIBUTE_NAME_TEXT, someNameGroup[i]);
            m.put(ATRIBUTE_NAME_IMAGE, imageAlbom);
            data.add(m);
        }

        String[] from = {ATRIBUTE_NAME_TEXT, ATRIBUTE_NAME_IMAGE};

        int[] to = {R.id.item_name_group, R.id.item_album_image};

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, data, R.layout.item, from, to);
        listView = (ListView) findViewById(R.id.list_item);
        listView.setAdapter(simpleAdapter);

        final Intent intent = new Intent(this, FileManager.class);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(intent);
            }
        });
    }
}