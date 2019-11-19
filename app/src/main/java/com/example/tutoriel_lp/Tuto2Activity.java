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
            this.listItems.add(inv.toString());
        }

        this.adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        list.setAdapter(adapter);

        addInvit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add = new Intent(Tuto2Activity.this, AddInvit.class);
                startActivityForResult(add, 1);
            }
        });


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Invitation invite = listInvit.get(position);

                Intent intent = new Intent(Tuto2Activity.this,InvitationDetails.class);
                intent.putExtra("invite",invite);
                intent.putExtra("index",position);

                startActivityForResult(intent, 2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println(resultCode);

        switch (resultCode){
            case 1:
                if(!data.hasExtra("invite_add")){
                    Log.i("msg", "pas d'extras");
                }
                else{
                    String inv_title = data.getStringExtra("invit_string");
                    Invitation invit = (Invitation) data.getSerializableExtra("invite_add");

                    this.adapter.add(inv_title);
                    this.listInvit.add(invit);

                    this.sortInvitations();
                    this.adapter.notifyDataSetChanged();
                }

            case 2:
                if(!data.hasExtra("invite_details")){
                    Log.i("LOL","details fail");
                }
                else{
                    Invitation invit = (Invitation) data.getSerializableExtra("invite_details");
                    int index = data.getIntExtra("index",1000);

                    this.listItems.set(index,invit.toString());
                    this.listInvit.set(index,invit);
                    this.adapter.notifyDataSetChanged();

                    this.sortInvitations();
                }
        }

    }


    public void sortInvitations(){
        int size_list = this.listItems.size();

        for (int i=0;i<=size_list-2;i++){
            System.out.println(size_list);
            boolean b_invit1 = this.listInvit.get(i).estConfirmee();
            boolean b_invit2 = this.listInvit.get(i+1).estConfirmee();

            Invitation invit1 = this.listInvit.get(i);
            Invitation invit2 = this.listInvit.get(i+1);

            System.out.println(b_invit1 + "  " + b_invit2);

            if((b_invit1 && !b_invit2) || (!b_invit1 && b_invit2))
                if(b_invit2){
                    System.out.println(this.listInvit.get(i).toString());

                    this.listInvit.set(i,invit2);
                    this.listInvit.set(i+1,invit1);

                    this.listItems.set(i,invit2.toString());
                    this.listItems.set(i+1,invit1.toString());
                }
        }
        this.adapter.notifyDataSetChanged();
    }
}
