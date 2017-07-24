package com.example.daniel.facerec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ReadyToSend extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ready_to_send);
        Button send = (Button) findViewById(R.id.button12);
     /*   send.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent i =
                        startActivity(i);
                    }
                }
        );*/
    }
}
