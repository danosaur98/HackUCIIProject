package com.example.daniel.facerec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ContactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        Button Daniel = (Button) findViewById(R.id.button3);
        Button William = (Button) findViewById(R.id.button2);
        Button Unknown = (Button) findViewById(R.id.button4);
        Daniel.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent i = new Intent(ContactsActivity.this, DanielZhouActivity.class);
                        startActivity(i);
                    }
                }
        );
        William.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent k = new Intent(ContactsActivity.this, WilliamLiActivity.class);
                        startActivity(k);
                    }
                }
        );
        Unknown.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent p = new Intent(ContactsActivity.this, ArjunActivity.class);
                        startActivity(p);
                    }
                }
        );
    }
}
