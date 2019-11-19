package com.example.tutoriel_lp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.ArrayList;

public class Tuto2Activity extends AppCompatActivity {

    private ArrayList<String> listItems=new ArrayList<String>();
    private ArrayList<Invitation> listInvit = new ArrayList<Invitation>();
    private final int FLAG_ACTIVITY = 1000;
    ArrayAdapter<String> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuto2);

        Button addInvit = (Button) findViewById(R.id.addInvit);
        Button sendList = (Button) findViewById(R.id.sendList);
        final ListView list = (ListView) findViewById(R.id.list);


        for(int i =1;i<=4;i++){
            Invitation inv = null;

            if(i==1){
                inv = new Invitation("Guigourez","Maxime");
                this.listInvit.add(inv);
            }else if(i==2){
                inv = new Invitation("Guillot","Lucas");
                this.listInvit.add(inv);
            }else if(i==3){
                inv = new Invitation("Thomas","Remi");
                this.listInvit.add(inv);
            }
            if(i==4){
                inv = new Invitation("Duchesne","Quentin");
                this.listInvit.add(inv);
            }

            if(inv.estConfirmee()){
                this.listItems.add(inv.toString() + "  Confirmee");
            }else{
                this.listItems.add(inv.toString() + "  Non Confirmee");
            }
        }

        this.adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        list.setAdapter(adapter);

        addInvit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add = new Intent(Tuto2Activity.this, AddInvit.class);
                startActivityForResult(add, FLAG_ACTIVITY);
            }
        });


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Invitation invite = listInvit.get(position);

                Intent intent = new Intent(Tuto2Activity.this,InvitationDetails.class);
                intent.putExtra("invite",invite);
                intent.putExtra("index",position);

                startActivityForResult(intent, FLAG_ACTIVITY);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((resultCode == RESULT_OK) && (requestCode == FLAG_ACTIVITY)) {

            if(!data.hasExtra("invite_add")){
                Log.i("msg", "pas d'extras add ");
            }
            else{
                String inv_title = data.getStringExtra("invit_string");
                Invitation invit = (Invitation) data.getSerializableExtra("invite_add");

                this.adapter.add(inv_title);
                this.listInvit.add(invit);
            }

            if(!data.hasExtra("invite_details")){
                Log.i("msg", "pas d'extras details ");
            }
            else{
                System.out.println("oui oui oui ");
                Invitation invit = (Invitation) data.getSerializableExtra("invite_details");
                int index = data.getIntExtra("index",1000);

                System.out.println(invit.toString());
            }
        }
    }

}
