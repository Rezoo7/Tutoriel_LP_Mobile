package com.example.tutoriel_lp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class InvitationDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitation_details);

        final EditText nom = (EditText) findViewById(R.id.nom_details);
        final EditText prenom = (EditText) findViewById(R.id.prenom_details);
        final CheckBox cb = (CheckBox) findViewById(R.id.cb_invit);
        Button retour = findViewById(R.id.retour);


        final Invitation inv = (Invitation) getIntent().getSerializableExtra("invite");
        final int index = getIntent().getIntExtra("index",1000);

        nom.setText(inv.nomInvite());
        prenom.setText(inv.prénomInvite());
        cb.setChecked(inv.estConfirmee());

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                inv.setNom(nom.getText().toString());
                inv.setPrénom(prenom.getText().toString());
                inv.confirmer(cb.isChecked());

                Intent intent = new Intent();
                intent.putExtra("invite_details",inv);
                intent.putExtra("index",index);

                finish();
            }
        });


    }
}
