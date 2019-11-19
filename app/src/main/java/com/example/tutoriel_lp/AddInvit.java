package com.example.tutoriel_lp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class AddInvit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        final EditText nom = (EditText) findViewById(R.id.nom);
        final EditText prenom = (EditText) findViewById(R.id.prenom);
        Button add = (Button) findViewById(R.id.btn_add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nom.getText().toString();
                String pre = prenom.getText().toString();
                Invitation invit = new Invitation(name,pre);

                Intent intent = new Intent();
                intent.putExtra("invit_string",invit.toString());
                intent.putExtra("invite_add",invit);

                setResult(1,intent);
                finish();
            }
        });
    }
}
