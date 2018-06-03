package com.example.newapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class Prompt extends WearableActivity {

    private TextView mTextView;
    public static Context mContext;
    private String recording_details;
    public static String EXTRA_MESSAGE = "com.example.newapp.MESSAGE";
    private int randomNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prompt);

        mTextView = (TextView) findViewById(R.id.text);

        Intent intent = getIntent();
        recording_details = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        File directory = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "Memories");
        directory.mkdirs();

        TextView tv1 = (TextView)findViewById(R.id.prompt);
        tv1.setText(R.string.ns_prompt);
        setAmbientEnabled();
    }

    public void initialBird(View view) throws IOException {
        Intent intent;
        String confirm;
        confirm = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss", Locale.US).format(new Date());
        recording_details = recording_details + ",Prompt selected at " + confirm;
        String message = recording_details;

        intent = new Intent(this, BirdBefore.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
        this.finish();
    }

    public void goVocab(View view) throws IOException {
        Intent intent;
        String decline;
        decline = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss", Locale.US).format(new Date());

        recording_details = recording_details + ",Prompt dismissed at " + decline + ",,,,,,,";
        try {
            //open file for writing
            File file = new File("/sdcard/Memories/recording_details.txt");
            FileOutputStream fileinput = new FileOutputStream(file, true);
            PrintStream printstream = new PrintStream(fileinput);
            printstream.print(recording_details+"\n");
            fileinput.close();


        } catch (java.io.IOException e) {
            //if caught

        }
        intent = new Intent(this, MainActivity.class);

        startActivity(intent);
        this.finish();
    }


}
