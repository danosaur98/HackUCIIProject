package com.example.daniel.facerec;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class ReadyToSend extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ready_to_send);
        Button send = (Button) findViewById(R.id.button11);
        send.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent picMessageIntent = new Intent(android.content.Intent.ACTION_SEND);
                        picMessageIntent.setType("image/jpeg");

                        File prom = new File(Environment.getExternalStoragePublicDirectory(
                                        Environment.DIRECTORY_DOWNLOADS),
                                "prom.JPG");
                        File hack = new File(Environment.getExternalStoragePublicDirectory(
                                Environment.DIRECTORY_DOWNLOADS),
                                "hack.JPG");
                        File grad = new File(Environment.getExternalStoragePublicDirectory(
                                Environment.DIRECTORY_DOWNLOADS),
                                "graduation.JPG");
                        picMessageIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(prom));
                        picMessageIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(hack));
                        picMessageIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(grad));
                        List<Uri> pathsDan = new ArrayList<>();
                        pathsDan.add(Uri.fromFile(prom));
                        pathsDan.add(Uri.fromFile(hack));

                        List<Uri>pathsWill = new ArrayList<>();
                        pathsWill.add(Uri.fromFile(prom));
                        pathsWill.add(Uri.fromFile(grad));
                        pathsWill.add(Uri.fromFile(hack));

                        List<Uri>pathsArjun = new ArrayList<>();
                        pathsArjun.add(Uri.fromFile(grad));
                        pathsArjun.add(Uri.fromFile(hack));


                        email(ReadyToSend.this, "arjun.stv@gmail.com", "", "Pictures",
                                "", pathsArjun);
                        email(ReadyToSend.this, "superwilliamli@yahoo.com", "", "Pictures",
                                "", pathsWill);
                        email(ReadyToSend.this, "danielzhou2012@gmail.com", "", "Pictures",
                                "", pathsDan);
                    }
                }
        );

    }

    public static void email(Context context, String emailTo, String emailCC,
                             String subject, String emailText, List<Uri> filePaths) {
        //need to "send multiple" to get more than one attachment
        final Intent emailIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
                new String[]{emailTo});
        emailIntent.putExtra(android.content.Intent.EXTRA_CC,
                new String[]{emailCC});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, emailText);
        //has to be an ArrayList
/*        ArrayList<Uri> uris = new ArrayList<Uri>();
        //convert from paths to Android friendly Parcelable Uri's
        for (String file : filePaths)
        {
            File fileIn = new File(file);
            Uri u = Uri.fromFile(fileIn);
            uris.add(u);
        } */
        emailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, (ArrayList) filePaths);
        context.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
    }
}
