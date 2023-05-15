package com.nlaurent.wordgametrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class WordGenerate extends AppCompatActivity {


    // Get the intent sent from MainActivity.
    private ImageButton yesButton;
    private ImageButton noButton;
    private String letter1;
    private String letter2;
    private ImageView firstLetter;
    private ImageView secondLetter;
    private TextView wordDefinition;
    private TextView test;
    private ImageButton nextButton;
    private static final String REAL = "real";
    private static final String NOT_REAL = "not real";
    private FrameLayout definitionBox;
    private ImageView yesCheck;
    private ImageView noCheck;
    private ImageView yesX;
    private ImageView noX;
    private static boolean wordsAlreadyGenerated;
    private ImageButton homeButtonWordGenerate;



    WordOperations wordOps = new WordOperations();
    MainActivity mainActivity = new MainActivity();
    static WordOperations.GeneratedWord currentGenWord = new WordOperations.GeneratedWord();

    public String addToWrite(int value, Context context){
        List<String> readList;
        String previousScoreString;
        int previousScore;
        int newScore;
        readList = Arrays.asList(mainActivity.readFromFile("currentTwoLetterScore.txt",context).split(", "));
        if(value == 0) {
            previousScoreString = readList.get(0).replace("[", "").replace("]", "");
            previousScore = Integer.parseInt(previousScoreString);
            newScore = previousScore + 1;
            return "[" + Integer.toString(newScore) + ", " + Arrays.asList(mainActivity.readFromFile("currentTwoLetterScore.txt",context).split(", ")).get(1);
        }
        else{
            previousScoreString = readList.get(1).replace("]","");
            previousScore = Integer.parseInt(previousScoreString);
            newScore = previousScore + 1;
            return Arrays.asList(mainActivity.readFromFile("currentTwoLetterScore.txt",context).split(", ")).get(0) + ", " + Integer.toString(newScore) + "]";
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_word_generate);
        // Find TextView by its ID

        this.firstLetter  = (ImageView)this.findViewById(R.id.firstLetter);
        this.secondLetter = (ImageView)this.findViewById(R.id.secondLetter);
        this.wordDefinition = (TextView)this.findViewById(R.id.wordDefinition);
        this.yesButton = (ImageButton)this.findViewById(R.id.yesButton);
        this.noButton = (ImageButton)this.findViewById(R.id.noButton);
        this.definitionBox = (FrameLayout)this.findViewById(R.id.definitionBox);
        this.yesCheck = (ImageView)this.findViewById(R.id.yesCheck);
        this.noCheck = (ImageView)this.findViewById(R.id.noCheck);
        this.yesX = (ImageView)this.findViewById(R.id.yesX);
        this.noX = (ImageView)this.findViewById(R.id.noX);
        this.homeButtonWordGenerate = (ImageButton)this.findViewById(R.id.homeButtonWordGenerate);
        AdView adView = (AdView) this.findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder().build();

        adView.loadAd(adRequest);



        // Find TextView by its ID
        Intent intent = getIntent();
        // Parameter in Intent, sent from MainActivity
        String value1 = intent.getStringExtra("text1");

        if(!wordsAlreadyGenerated) {
            wordOps.initWords();
            currentGenWord = wordOps.generateWord();
            wordsAlreadyGenerated = true;

        }
        if(Arrays.asList(mainActivity.readFromFile("currentTwoLetterScore.txt",getApplicationContext()).split(", ")).size() == 1){
            mainActivity.writeToFile("currentTwoLetterScore.txt",Arrays.asList(0,0).toString(),getApplicationContext());
        }
        // Parameter in Intent, sent from MainActivity
        String value2 = intent.getStringExtra("text2");
        String test = intent.getStringExtra("test");
        letter1 = String.valueOf(currentGenWord.getCurrentWord().charAt(0));
        letter1 = letter1.toLowerCase(Locale.ROOT);
        letter2 = String.valueOf(currentGenWord.getCurrentWord().charAt(1));
        letter2 = letter2.toLowerCase(Locale.ROOT);

        Context context = firstLetter.getContext();
        int id = context.getResources().getIdentifier(letter1, "drawable", context.getPackageName());
        firstLetter.setImageResource(id);

        context = secondLetter.getContext();
        id = context.getResources().getIdentifier(letter2, "drawable", context.getPackageName());
        secondLetter.setImageResource(id);
/*
        RotateAnimation anim = new RotateAnimation(0f, 350f, 15f, 15f);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(Animation.ABSOLUTE);
        anim.setDuration(700);



        firstLetter.startAnimation(anim);
*/



        String realNotReal = "";
        if(currentGenWord.isWordReal())
        {
            realNotReal = REAL;
            this.wordDefinition.setText(currentGenWord.currentDefinition);
        }
        else
        {
            realNotReal = NOT_REAL;
        }

        this.nextButton = (ImageButton) this.findViewById(R.id.nextButton);

        yesButton.setOnClickListener(new Button.OnClickListener() {


            @Override
            public void onClick(View v) {
                wordsAlreadyGenerated = false;
                if(currentGenWord.isWordReal()){
                    definitionBox.setVisibility(View.VISIBLE);
                    yesCheck.setVisibility(View.VISIBLE);
                    SessionStatistics.scores.set(0,SessionStatistics.scores.get(0) + 1);
                    mainActivity.writeToFile("currentTwoLetterScore.txt",addToWrite(0,getApplicationContext()),getApplicationContext());
               }else{
                    yesX.setVisibility(View.VISIBLE);
                    SessionStatistics.scores.set(1,SessionStatistics.scores.get(1) + 1);
                    mainActivity.writeToFile("currentTwoLetterScore.txt",addToWrite(1,getApplicationContext()),getApplicationContext());
                }
                nextButton.setVisibility(View.VISIBLE);
                nextButton.setClickable(true);
                noButton.setClickable(false);
            }
        });
        noButton.setOnClickListener(new Button.OnClickListener() {


            @Override
            public void onClick(View v) {
                wordsAlreadyGenerated = false;
                if(!currentGenWord.isWordReal()){
                    noCheck.setVisibility(View.VISIBLE);

                    SessionStatistics.scores.set(0,SessionStatistics.scores.get(0) + 1);
                    mainActivity.writeToFile("currentTwoLetterScore.txt",addToWrite(0,getApplicationContext()),getApplicationContext());
                }else {
                    definitionBox.setVisibility(View.VISIBLE);
                    noX.setVisibility(View.VISIBLE);
                    SessionStatistics.scores.set(1,SessionStatistics.scores.get(1) + 1);
                    mainActivity.writeToFile("currentTwoLetterScore.txt",addToWrite(1,getApplicationContext()),getApplicationContext());




                }
                nextButton.setVisibility(View.VISIBLE);
                nextButton.setClickable(true);
                yesButton.setClickable(false);
            }
        });
        nextButton.setOnClickListener(new Button.OnClickListener() {


            @Override
            public void onClick(View v) {

                // Create a Intent:
                // (This object contains content that will be sent to Example1Activity).

                Intent myIntent = new Intent(WordGenerate.this, WordGenerate.class);
                // Parameter for Intent.


                // Start Example1Activity.
                WordGenerate.this.startActivity(myIntent);

            }

        });
        homeButtonWordGenerate.setOnClickListener(new Button.OnClickListener() {


            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(WordGenerate.this, MainActivity.class);
                // Parameter for Intent.


                // Start Example1Activity.
                WordGenerate.this.startActivity(myIntent);

            }

        });


    }
    @Override
    public void onBackPressed()  {


        Intent myIntent = new Intent(WordGenerate.this,MainActivity.class);
        WordGenerate.this.startActivity(myIntent);
    }

    public WordOperations.GeneratedWord getCurrentGenWord() {
        return currentGenWord;
    }
}