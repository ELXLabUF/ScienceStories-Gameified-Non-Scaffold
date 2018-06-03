package com.example.newapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class BirdAfter extends WearableActivity {

    private TextView mTextView;
    private String recording_details = "";
    public static String EXTRA_MESSAGE = "com.example.newapp.MESSAGE";
    public static int numberOfRecordings;
    private String firstLine;
    private String temp = "";
    private int randomNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bird_after);

        mTextView = (TextView) findViewById(R.id.text);

        Intent intent = getIntent();
        recording_details = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        //setContentView(R.layout.rect_activity_bird_before);

        // Enables Always-on
        setAmbientEnabled();

        numberOfRecordings = numRecordings();
        int birdLevel = numberOfRecordings/2;
        final ImageView birdImage = (ImageView) findViewById(R.id.bird);

        String[] arr;
        int[] numOccurrences = new int[3];

        try {
            firstLine = getFirstLine();
        } catch (IOException e) {
            // TODO: error handling
        }

        if(firstLine != null && !firstLine.isEmpty()) {
            Log.d("firstLine is", firstLine);
            arr = firstLine.split(",");
            for(int i=0;i < arr.length;i++){
                Log.d("arr is", arr[i]);
                numOccurrences[i] = Integer.parseInt(arr[i]);
            }
        }

        randomNum = getRandomNumberInRange(1,3);

        numOccurrences[randomNum - 1] = numOccurrences[randomNum - 1] + 1;

        for(int i = 0; i < numOccurrences.length; i++) {
            temp = temp + numOccurrences[i] + ",";
        }

        Log.d("temp is", temp);
        temp = temp.substring(0, temp.length() - 1);
        Log.d("temp is", temp);

        try {
            writeToOne(temp);
        } catch (IOException e) {
            // TODO: error handling
        }



        int observationLevel = numOccurrences[0]/2;
        int activityLevel = numOccurrences[1]/2;
        int experimentLevel = numOccurrences[2]/2;

        Log.d("birdLevel is", Integer.toString(birdLevel));
        Log.d("observationLevel is", Integer.toString(observationLevel));
        Log.d("activityLevel is", Integer.toString(activityLevel));
        Log.d("experimentLevel is", Integer.toString(experimentLevel));
        Log.d("randomNum is", Integer.toString(randomNum));

        if(birdLevel == 0)
            birdImage.setBackground(getResources().getDrawable(R.drawable.egg));
        else if(birdLevel >= 5) {
            if(experimentLevel == 0)
                birdImage.setBackground(getResources().getDrawable(R.drawable.bird5));
            else if(experimentLevel >= 5)
                birdImage.setBackground(getResources().getDrawable(R.drawable.color5_5));
            else {
                switch (experimentLevel) {
                    case 1:
                        birdImage.setBackground(getResources().getDrawable(R.drawable.color1_5));
                        break;
                    case 2:
                        birdImage.setBackground(getResources().getDrawable(R.drawable.color2_5));
                        break;
                    case 3:
                        birdImage.setBackground(getResources().getDrawable(R.drawable.color3_5));
                        break;
                    case 4:
                        birdImage.setBackground(getResources().getDrawable(R.drawable.color4_5));
                        break;
                }
            }

        }
        else if(birdLevel == 1) {
            if(experimentLevel == 0)
                birdImage.setBackground(getResources().getDrawable(R.drawable.bird1));
            else if(experimentLevel >= 5)
                birdImage.setBackground(getResources().getDrawable(R.drawable.color5_1));
            else {
                switch (experimentLevel) {
                    case 1:
                        birdImage.setBackground(getResources().getDrawable(R.drawable.color1_1));
                        break;
                    case 2:
                        birdImage.setBackground(getResources().getDrawable(R.drawable.color2_1));
                        break;
                    case 3:
                        birdImage.setBackground(getResources().getDrawable(R.drawable.color3_1));
                        break;
                    case 4:
                        birdImage.setBackground(getResources().getDrawable(R.drawable.color4_1));
                        break;
                }
            }
        }
        else if(birdLevel == 2) {
            if(experimentLevel == 0)
                birdImage.setBackground(getResources().getDrawable(R.drawable.bird2));
            else if(experimentLevel >= 5)
                birdImage.setBackground(getResources().getDrawable(R.drawable.color5_2));
            else {
                switch (experimentLevel) {
                    case 1:
                        birdImage.setBackground(getResources().getDrawable(R.drawable.color1_2));
                        break;
                    case 2:
                        birdImage.setBackground(getResources().getDrawable(R.drawable.color2_2));
                        break;
                    case 3:
                        birdImage.setBackground(getResources().getDrawable(R.drawable.color3_2));
                        break;
                    case 4:
                        birdImage.setBackground(getResources().getDrawable(R.drawable.color4_2));
                        break;
                }
            }
        }
        else if(birdLevel == 3) {
            if(experimentLevel == 0)
                birdImage.setBackground(getResources().getDrawable(R.drawable.bird3));
            else if(experimentLevel >= 5)
                birdImage.setBackground(getResources().getDrawable(R.drawable.color5_3));
            else {
                switch (experimentLevel) {
                    case 1:
                        birdImage.setBackground(getResources().getDrawable(R.drawable.color1_3));
                        break;
                    case 2:
                        birdImage.setBackground(getResources().getDrawable(R.drawable.color2_3));
                        break;
                    case 3:
                        birdImage.setBackground(getResources().getDrawable(R.drawable.color3_3));
                        break;
                    case 4:
                        birdImage.setBackground(getResources().getDrawable(R.drawable.color4_3));
                        break;
                }
            }
        }
        else if(birdLevel == 4) {
            if(experimentLevel == 0)
                birdImage.setBackground(getResources().getDrawable(R.drawable.bird4));
            else if(experimentLevel >= 5)
                birdImage.setBackground(getResources().getDrawable(R.drawable.color5_4));
            else {
                switch (experimentLevel) {
                    case 1:
                        birdImage.setBackground(getResources().getDrawable(R.drawable.color1_4));
                        break;
                    case 2:
                        birdImage.setBackground(getResources().getDrawable(R.drawable.color2_4));
                        break;
                    case 3:
                        birdImage.setBackground(getResources().getDrawable(R.drawable.color3_4));
                        break;
                    case 4:
                        birdImage.setBackground(getResources().getDrawable(R.drawable.color4_4));
                        break;
                }
            }
        }


        final ImageView featherImage = (ImageView) findViewById(R.id.feather);
        final ImageView accImage = (ImageView) findViewById(R.id.accessory);

        if(observationLevel >= 5)
            featherImage.setBackground(getResources().getDrawable(R.drawable.feather5));
        switch(observationLevel) {
            case 1:
                featherImage.setBackground(getResources().getDrawable(R.drawable.feather1));
                break;
            case 2:
                featherImage.setBackground(getResources().getDrawable(R.drawable.feather2));
                break;
            case 3:
                featherImage.setBackground(getResources().getDrawable(R.drawable.feather3));
                break;
            case 4:
                featherImage.setBackground(getResources().getDrawable(R.drawable.feather4));
                break;
        }

        if(activityLevel >= 5)
            accImage.setBackground(getResources().getDrawable(R.drawable.acc5));
        switch(activityLevel) {
            case 1:
                accImage.setBackground(getResources().getDrawable(R.drawable.acc1));
                break;
            case 2:
                accImage.setBackground(getResources().getDrawable(R.drawable.acc2));
                break;
            case 3:
                accImage.setBackground(getResources().getDrawable(R.drawable.acc3));
                break;
            case 4:
                accImage.setBackground(getResources().getDrawable(R.drawable.acc4));
                break;
        }
    }

    public void goBack(View view) throws IOException {
        Intent intent;
        String confirmTime = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss", Locale.US).format(new Date());
        recording_details = recording_details + "," + confirmTime;
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
        //String message = recording_details;
        intent = new Intent(this, MainActivity.class);
        //intent.putExtra(EXTRA_MESSAGE, message);
        //writeToOne(firstLine, temp);
        startActivity(intent);
        this.finish();
    }

    public static int numRecordings() {
        int numberOfRecordings = 0;
        File Memories = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator + "Memories");
        if(Memories.isDirectory()) {
            File[] foundFiles = Memories.listFiles(new FilenameFilter() {
                public boolean accept(File Memories, String name) {
                    return name.endsWith(".wav");
                }
            });
            numberOfRecordings = foundFiles.length;
            return numberOfRecordings;
        }
        return 0;
    }

    public static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static String getFirstLine() throws IOException {

        String strLine = null, tmp;
        File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator + "Memories" + File.separator + "birdStatus.txt");
        if(f.exists() && !f.isDirectory()) {
            try {
                FileInputStream in = new FileInputStream(Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator + "Memories" + File.separator + "birdStatus.txt");
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    while ((tmp = br.readLine()) != null) {
                        strLine = tmp;
                        break;
                    }
                } finally {
                    in.close();
                }

            } catch (IOException e) {
                // TODO: error handling
            }


        }
        return strLine;
    }

    public static void writeToOne(String firstLine) throws IOException {
        String FILENAME = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "Memories" + File.separator + "birdStatus.txt";
        BufferedWriter bw = null;
        FileWriter fw = null;
        File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator + "Memories" + File.separator + "birdStatus.txt");
        if(f.exists() && !f.isDirectory()) {
            try {
                //open file for writing
                File file = new File("/sdcard/Memories/birdStatus.txt");
                FileOutputStream fileinput = new FileOutputStream(file, false);
                PrintStream printstream = new PrintStream(fileinput);
                printstream.print(firstLine+"\n");
                fileinput.close();


            } catch (java.io.IOException e) {
                //if caught

            }
        }
        else {
            File dir = new File("/sdcard/Memories");
            try{
                if(dir.mkdir()) {
                    Log.d("Directory created: ", "Success");
                } else {
                    Log.d("Directory not created: ", "Failure");
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            try {
                //open file for writing
                File file = new File("/sdcard/Memories/birdStatus.txt");
                FileOutputStream fileinput = new FileOutputStream(file, false);
                PrintStream printstream = new PrintStream(fileinput);
                printstream.print(firstLine+"\n");
                fileinput.close();


            } catch (java.io.IOException e) {
                //if caught

            }
        }

    }
}
