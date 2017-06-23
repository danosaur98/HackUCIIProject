package com.example.daniel.facerec;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;

import android.provider.MediaStore.Images;
import android.provider.MediaStore.Images.Media;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

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
                        email(ReadyToSend.this, "danielzhou2012@gmail.com", "", "Pictures",
                                "", pathsDan);
                        List<Uri>pathsWill = new ArrayList<>();
                        pathsWill.add(Uri.fromFile(prom));
                        pathsWill.add(Uri.fromFile(grad));
                        pathsWill.add(Uri.fromFile(hack));
                        email(ReadyToSend.this, "superwilliamli@yahoo.com", "", "Pictures",
                                "", pathsWill);
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
