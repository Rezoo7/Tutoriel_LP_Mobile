package com.example.tutoriel_lp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    public MainActivity(){


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText original = (EditText) findViewById(R.id.original);
        final EditText chiffre = (EditText) findViewById(R.id.chiffre);
        final SeekBar  decalage = (SeekBar) findViewById(R.id.decalage);
        final Button btn_chiffre = (Button) findViewById(R.id.btn_chiffre);
        final EditText text_decal = (EditText) findViewById(R.id.nombre_decal);
        Button btn_effacer = (Button) findViewById(R.id.btn_effacer);
        final Button tuto2 = (Button) findViewById(R.id.tuto2);


        text_decal.setEnabled(false);
        chiffre.setEnabled(false);
        decalage.setMax(26);
        decalage.setProgress(1);

        decalage.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                text_decal.setText(""+i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        btn_chiffre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChiffreCesar chc = new ChiffreCesar();

                int decal = decalage.getProgress();
                String text = original.getText().toString();
                String code = chc.chiffrement(text,decal);

                chiffre.setText(code);
            }
        });

        btn_effacer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                original.setText("");
                chiffre.setText("");
            }
        });

        tuto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tuto2 = new Intent(MainActivity.this,Tuto2Activity.class);
                startActivity(tuto2);
            }
        });

    }
}
