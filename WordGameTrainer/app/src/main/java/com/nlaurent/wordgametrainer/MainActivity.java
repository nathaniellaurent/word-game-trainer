package com.nlaurent.wordgametrainer;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import android.view.View;
import androidx.navigation.ui.AppBarConfiguration;


import com.nlaurent.wordgametrainer.databinding.ActivityMainBinding;


import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;



public class MainActivity extends Activity {

    private ImageButton goToOneWordGenerate;
    private ImageButton goToSixWordGenerate;
    private ImageButton goTo1W3LGenerate;
    private ImageButton goTo6W3LGenerate;
    private ImageButton statsButton;
    private Button statsButtonSixWords;
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    protected static WordList myWords;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

      //  SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getSupportActionBar().hide();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        // Find Button by its ID
        this.goToOneWordGenerate = (ImageButton) this.findViewById(R.id.goToOneWordGenerate);
        this.goToSixWordGenerate = (ImageButton) this.findViewById(R.id.goToSixWordGenerate);
        this.goTo1W3LGenerate = (ImageButton) this.findViewById(R.id.goTo1W3LGenerate);
        this.goTo6W3LGenerate = (ImageButton) this.findViewById(R.id.goTo6W3LGenerate);
        this.statsButton = (ImageButton) this.findViewById((R.id.statsButton1));
        this.statsButtonSixWords = (Button) this.findViewById((R.id.statsButtonSixWords));
        AdView adView = (AdView) this.findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder()
     //           .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
    //    adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
    //    adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        adView.loadAd(adRequest);

        parseWords();



// Register listener user clicks on the goToOneWordGenerate.

        goToOneWordGenerate.setOnClickListener(new Button.OnClickListener() {


            @Override
            public void onClick(View v) {


                // Create a Intent:
                // (This object contains content that will be sent to Example1Activity).

                Intent myIntent = new Intent(MainActivity.this, WordGenerate.class);
                // Parameter for Intent.


                // Start Example1Activity.
                MainActivity.this.startActivity(myIntent);

            }
        });
        goTo1W3LGenerate.setOnClickListener(new Button.OnClickListener() {


            @Override
            public void onClick(View v) {


                // Create a Intent:
                // (This object contains content that will be sent to Example1Activity).

                Intent myIntent = new Intent(MainActivity.this, ThreeLetterGenerate.class);
                // Parameter for Intent.


                // Start Example1Activity.
                MainActivity.this.startActivity(myIntent);

            }
        });
        goToSixWordGenerate.setOnClickListener(new Button.OnClickListener() {


            @Override
            public void onClick(View v) {


                // Create a Intent:
                // (This object contains content that will be sent to Example1Activity).

                Intent myIntent = new Intent(MainActivity.this, SixWordGenerate.class);
                // Parameter for Intent.


                // Start Example1Activity.
                MainActivity.this.startActivity(myIntent);

            }
        });
        goTo6W3LGenerate.setOnClickListener(new Button.OnClickListener() {


            @Override
            public void onClick(View v) {


                // Create a Intent:
                // (This object contains content that will be sent to Example1Activity).

                Intent myIntent = new Intent(MainActivity.this, SixWordGenerate3L.class);
                // Parameter for Intent.


                // Start Example1Activity.
                MainActivity.this.startActivity(myIntent);

            }
        });
        statsButton.setOnClickListener(new Button.OnClickListener() {


            @Override
            public void onClick(View v) {


                // Create a Intent:
                // (This object contains content that will be sent to Example1Activity).

                Intent myIntent = new Intent(MainActivity.this, SessionStatistics.class);
                // Parameter for Intent.


                // Start Example1Activity.
                MainActivity.this.startActivity(myIntent);

            }
        });
        statsButtonSixWords.setOnClickListener(new Button.OnClickListener() {


            @Override
            public void onClick(View v) {


                // Create a Intent:
                // (This object contains content that will be sent to Example1Activity).

                Intent myIntent = new Intent(MainActivity.this, ThreeLetterSessionStatistics.class);
                // Parameter for Intent.


                // Start Example1Activity.
                MainActivity.this.startActivity(myIntent);

            }
        });

    }
    @Override
    public void onBackPressed()  {

        Intent myIntent = new Intent(MainActivity.this,MainActivity.class);
        MainActivity.this.startActivity(myIntent);
    }
    public void writeToFile(String fileName, String content, Context context){
        File path = context.getFilesDir();
        try{
            FileOutputStream writer = new FileOutputStream(new File(path, fileName));
            writer.write(content.getBytes());
            writer.close();
            Toast.makeText(context,"Wrote to file:" + fileName,Toast.LENGTH_SHORT);
        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(context,"Failed to write",Toast.LENGTH_SHORT);
        }
    }
    public String readFromFile(String fileName, Context context){

        File path = context.getFilesDir();
        File readFrom = new File(path, fileName);
        byte[] content = new byte[(int) readFrom.length()];
        try {
            FileInputStream stream = new FileInputStream(readFrom);
            stream.read(content);
            return new String(content);
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }
    public void parseWords(){
        try {
            InputStream inputStream = getAssets().open("scrabble_words.json");
            ObjectMapper mapper = new ObjectMapper();
            this.myWords = mapper.readValue(inputStream, WordList.class);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

}
