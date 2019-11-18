package com.example.tutoriel_lp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.ArrayList;

public class Tuto2Activity extends AppCompatActivity {

    private ArrayList<String> listItems=new ArrayList<String>();
    private final int FLAG_ACTIVITY = 1000;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuto2);

        Button addInvit = (Button) findViewById(R.id.addInvit);
        Button sendList = (Button) findViewById(R.id.sendList);
        ListView list = (ListView) findViewById(R.id.list);

        Invitation inv1 = new Invitation("Guigourez","Maxime");
        Invitation inv2 = new Invitation("Guillot","Lucas");
        Invitation inv3 = new Invitation("Thomas","Remi");
        Invitation inv4 = new Invitation("Duchesne","Quentin");

        this.listItems.add(inv1.toString());
        this.listItems.add(inv2.toString());
        this.listItems.add(inv3.toString());
        this.listItems.add(inv4.toString());

        this.adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        list.setAdapter(adapter);

        addInvit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add = new Intent(Tuto2Activity.this, AddInvit.class);
                startActivityForResult(add, FLAG_ACTIVITY);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((resultCode == RESULT_OK) && (requestCode == FLAG_ACTIVITY)) {

            if(!data.hasExtra("invit")){
                Log.i("msg", "pas d'extras");
            }
            else{
                Log.i("msg","oui oui oui");
                String invitation = data.getStringExtra("invit");
                this.adapter.add(invitation);
            }
        }
    }

}
