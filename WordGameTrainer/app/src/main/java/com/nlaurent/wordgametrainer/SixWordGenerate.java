package com.nlaurent.wordgametrainer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;


public class SixWordGenerate extends AppCompatActivity {


    // Get the intent sent from MainActivity.
    private ImageButton checkButton;
    private ImageButton word1Button;
    private ImageButton word2Button;
    private ImageButton word3Button;
    private ImageButton word4Button;
    private ImageButton word5Button;
    private ImageButton word6Button;
    private ImageButton nextButton;
    private ImageButton homeButtonSixWordGenerate;
    private ImageView score;
    private static final String REAL = "real";
    private static final String NOT_REAL = "not real";
    private ImageView word1Check;
    private ImageView word2Check;
    private ImageView word3Check;
    private ImageView word4Check;
    private ImageView word5Check;
    private ImageView word6Check;
    private ImageView word1X;
    private ImageView word2X;
    private ImageView word3X;
    private ImageView word4X;
    private ImageView word5X;
    private ImageView word6X;
    private boolean word1Pressed = false;
    private boolean word2Pressed = false;
    private boolean word3Pressed = false;
    private boolean word4Pressed = false;
    private boolean word5Pressed = false;
    private boolean word6Pressed = false;
    private int correctWords = 0;
    private static boolean wordsAlreadyGenerated;
    private ImageView W1L1, W1L2, W2L1, W2L2, W3L1, W3L2, W4L1, W4L2, W5L1, W5L2, W6L1, W6L2;
    private String W1L1s, W1L2s, W2L1s, W2L2s, W3L1s, W3L2s, W4L1s, W4L2s, W5L1s, W5L2s, W6L1s, W6L2s;

    MainActivity mainActivity = new MainActivity();
    WordOperations wordOps = new WordOperations();

    static WordOperations.GeneratedWord genWord1 = new WordOperations.GeneratedWord();
    static WordOperations.GeneratedWord genWord2 = new WordOperations.GeneratedWord();
    static WordOperations.GeneratedWord genWord3 = new WordOperations.GeneratedWord();
    static WordOperations.GeneratedWord genWord4 = new WordOperations.GeneratedWord();
    static WordOperations.GeneratedWord genWord5 = new WordOperations.GeneratedWord();
    static WordOperations.GeneratedWord genWord6 = new WordOperations.GeneratedWord();
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
    public int operateImages(ImageView wordCheck, ImageView wordX, ImageButton currentButton, boolean buttonPressed, boolean isReal, int currentCorrectWords, Context context) {
        currentButton.setClickable(false);
        MainActivity mainActivity = new MainActivity();
        if(Arrays.asList(mainActivity.readFromFile("currentTwoLetterScore.txt",context).split(", ")).size() == 1){
            mainActivity.writeToFile("currentTwoLetterScore.txt",Arrays.asList(0,0).toString(),context);
        }
        if(buttonPressed && isReal){
            wordCheck.setVisibility(View.VISIBLE);
            ThreeLetterSessionStatistics.threeLetterScores.set(0, ThreeLetterSessionStatistics.threeLetterScores.get(0) + 1);

            mainActivity.writeToFile("currentTwoLetterScore.txt",addToWrite(0,context),context);
            return 1;

        }
        else if(!buttonPressed && !isReal) {
            wordCheck.setVisibility(View.VISIBLE);
            ThreeLetterSessionStatistics.threeLetterScores.set(0, ThreeLetterSessionStatistics.threeLetterScores.get(0) + 1);
            mainActivity.writeToFile("currentTwoLetterScore.txt",addToWrite(0,context),context);
            return 1;
        }
        else{
            wordX.setVisibility(View.VISIBLE);
            ThreeLetterSessionStatistics.threeLetterScores.set(1, ThreeLetterSessionStatistics.threeLetterScores.get(1) + 1);
            mainActivity.writeToFile("currentTwoLetterScore.txt",addToWrite(1,context),context);
            return 0;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.six_word_generate);

        // Find TextView by its ID
        this.checkButton = (ImageButton)this.findViewById(R.id.checkButton);
        this.word1Button = (ImageButton)this.findViewById(R.id.word1);
        this.word2Button = (ImageButton)this.findViewById(R.id.word2);
        this.word3Button = (ImageButton)this.findViewById(R.id.word3);
        this.word4Button = (ImageButton)this.findViewById(R.id.word4);
        this.word5Button = (ImageButton)this.findViewById(R.id.word5);
        this.word6Button = (ImageButton)this.findViewById(R.id.word6);
        this.homeButtonSixWordGenerate = (ImageButton)this.findViewById(R.id.homeButtonSixWordGenerate);
        this.word1Check = (ImageView)this.findViewById(R.id.yesCheck);
        this.word2Check = (ImageView)this.findViewById(R.id.noCheck);
        this.word3Check = (ImageView)this.findViewById(R.id.word3Check);
        this.word4Check = (ImageView)this.findViewById(R.id.word4Check);
        this.word5Check = (ImageView)this.findViewById(R.id.word5Check);
        this.word6Check = (ImageView)this.findViewById(R.id.word6Check);
        this.word1X = (ImageView)this.findViewById(R.id.yesX);
        this.word2X = (ImageView)this.findViewById(R.id.word2X);
        this.word3X = (ImageView)this.findViewById(R.id.word3X);
        this.word4X = (ImageView)this.findViewById(R.id.word4X);
        this.word5X = (ImageView)this.findViewById(R.id.word5X);
        this.word6X = (ImageView)this.findViewById(R.id.word6X);
        this.score = (ImageView)this.findViewById(R.id.score);
        this.W1L1 = (ImageView)this.findViewById(R.id.W1L1);
        this.W1L2 = (ImageView)this.findViewById(R.id.W1L2);
        this.W2L1 = (ImageView)this.findViewById(R.id.W2L1);
        this.W2L2 = (ImageView)this.findViewById(R.id.W2L2);
        this.W3L1 = (ImageView)this.findViewById(R.id.W3L1);
        this.W3L2 = (ImageView)this.findViewById(R.id.W3L2);
        this.W4L1 = (ImageView)this.findViewById(R.id.W4L1);
        this.W4L2 = (ImageView)this.findViewById(R.id.W4L2);
        this.W5L1 = (ImageView)this.findViewById(R.id.W5L1);
        this.W5L2 = (ImageView)this.findViewById(R.id.W5L2);
        this.W6L1 = (ImageView)this.findViewById(R.id.W6L1);
        this.W6L2 = (ImageView)this.findViewById(R.id.W6L2);
        AdView adView = (AdView) this.findViewById(R.id.adView2);

        AdRequest adRequest = new AdRequest.Builder().build();

        adView.loadAd(adRequest);

        word1Button.setImageResource(android.R.color.transparent);
        word2Button.setImageResource(android.R.color.transparent);
        word3Button.setImageResource(android.R.color.transparent);
        word4Button.setImageResource(android.R.color.transparent);
        word5Button.setImageResource(android.R.color.transparent);
        word6Button.setImageResource(android.R.color.transparent);




        // Find TextView by its ID
        Intent intent = getIntent();
        // Parameter in Intent, sent from MainActivity
        String value1 = intent.getStringExtra("text1");
        wordOps.initWords();
        if(!wordsAlreadyGenerated) {
            genWord1 = wordOps.generateWord();
            genWord2 = wordOps.generateWord();
            genWord3 = wordOps.generateWord();
            genWord4 = wordOps.generateWord();
            genWord5 = wordOps.generateWord();
            genWord6 = wordOps.generateWord();
            wordsAlreadyGenerated = true;
        }

        // Parameter in Intent, sent from MainActivity
        String value2 = intent.getStringExtra("text2");
        String test = intent.getStringExtra("test");

        CharSequence word1Text = genWord1.currentWord;
        CharSequence word2Text = genWord2.currentWord;
        CharSequence word3Text = genWord3.currentWord;
        CharSequence word4Text = genWord4.currentWord;
        CharSequence word5Text = genWord5.currentWord;
        CharSequence word6Text = genWord6.currentWord;


        W1L1s = String.valueOf(genWord1.currentWord.charAt(0));
        W1L1s = W1L1s.toLowerCase(Locale.ROOT);
        W1L2s = String.valueOf(genWord1.currentWord.charAt(1));
        W1L2s = W1L2s.toLowerCase(Locale.ROOT);
        W2L1s = String.valueOf(genWord2.currentWord.charAt(0));
        W2L1s = W2L1s.toLowerCase(Locale.ROOT);
        W2L2s = String.valueOf(genWord2.currentWord.charAt(1));
        W2L2s = W2L2s.toLowerCase(Locale.ROOT);
        W3L1s = String.valueOf(genWord3.currentWord.charAt(0));
        W3L1s = W3L1s.toLowerCase(Locale.ROOT);
        W3L2s = String.valueOf(genWord3.currentWord.charAt(1));
        W3L2s = W3L2s.toLowerCase(Locale.ROOT);
        W4L1s = String.valueOf(genWord4.currentWord.charAt(0));
        W4L1s = W4L1s.toLowerCase(Locale.ROOT);
        W4L2s = String.valueOf(genWord4.currentWord.charAt(1));
        W4L2s = W4L2s.toLowerCase(Locale.ROOT);
        W5L1s = String.valueOf(genWord5.currentWord.charAt(0));
        W5L1s = W5L1s.toLowerCase(Locale.ROOT);
        W5L2s = String.valueOf(genWord5.currentWord.charAt(1));
        W5L2s = W5L2s.toLowerCase(Locale.ROOT);
        W6L1s = String.valueOf(genWord6.currentWord.charAt(0));
        W6L1s = W6L1s.toLowerCase(Locale.ROOT);
        W6L2s = String.valueOf(genWord6.currentWord.charAt(1));
        W6L2s = W6L2s.toLowerCase(Locale.ROOT);

        Context context = W1L1.getContext();
        int id = context.getResources().getIdentifier(W1L1s, "drawable", context.getPackageName());
        W1L1.setImageResource(id);

        context = W1L2.getContext();
        id = context.getResources().getIdentifier(W1L2s, "drawable", context.getPackageName());
        W1L2.setImageResource(id);

        context = W2L1.getContext();
        id = context.getResources().getIdentifier(W2L1s, "drawable", context.getPackageName());
        W2L1.setImageResource(id);

        context = W2L2.getContext();
        id = context.getResources().getIdentifier(W2L2s, "drawable", context.getPackageName());
        W2L2.setImageResource(id);

        context = W3L1.getContext();
        id = context.getResources().getIdentifier(W3L1s, "drawable", context.getPackageName());
        W3L1.setImageResource(id);

        context = W3L2.getContext();
        id = context.getResources().getIdentifier(W3L2s, "drawable", context.getPackageName());
        W3L2.setImageResource(id);

        context = W4L1.getContext();
        id = context.getResources().getIdentifier(W4L1s, "drawable", context.getPackageName());
        W4L1.setImageResource(id);

        context = W4L2.getContext();
        id = context.getResources().getIdentifier(W4L2s, "drawable", context.getPackageName());
        W4L2.setImageResource(id);

        context = W5L1.getContext();
        id = context.getResources().getIdentifier(W5L1s, "drawable", context.getPackageName());
        W5L1.setImageResource(id);

        context = W5L2.getContext();
        id = context.getResources().getIdentifier(W5L2s, "drawable", context.getPackageName());
        W5L2.setImageResource(id);

        context = W6L1.getContext();
        id = context.getResources().getIdentifier(W6L1s, "drawable", context.getPackageName());
        W6L1.setImageResource(id);

        context = W6L2.getContext();
        id = context.getResources().getIdentifier(W6L2s, "drawable", context.getPackageName());
        W6L2.setImageResource(id);



        String realNotReal = "";
        if(genWord1.isWordReal())
        {
            realNotReal = REAL;
        }
        else
        {
            realNotReal = NOT_REAL;
        }
        this.nextButton = (ImageButton) this.findViewById(R.id.nextButton);

        word1Button.setOnClickListener(new Button.OnClickListener() {
            boolean clicked = false;

            @Override
            public void onClick(View v) {

                if(!clicked) {
                    word1Button.setImageResource(R.drawable.rectangle_1);
                    clicked = true;
                    word1Pressed = true;

                }else{
                    word1Button.setImageResource(android.R.color.transparent);

                    clicked = false;
                    word1Pressed = false;
                }

            }
        });
        word2Button.setOnClickListener(new Button.OnClickListener() {
            boolean clicked = false;

            @Override
            public void onClick(View v) {

                if(!clicked) {
                    word2Button.setImageResource(R.drawable.rectangle_1);
                    clicked = true;
                    word2Pressed = true;
                }else{

                    word2Button.setImageResource(android.R.color.transparent);
                    clicked = false;
                    word2Pressed = false;
                }

            }
        });
        word3Button.setOnClickListener(new Button.OnClickListener() {
            boolean clicked = false;

            @Override
            public void onClick(View v) {

                if(!clicked) {
                    word3Button.setImageResource(R.drawable.rectangle_1);

                    clicked = true;
                    word3Pressed = true;
                }else{
                    word3Button.setImageResource(android.R.color.transparent);
                    clicked = false;
                    word3Pressed = false;
                }

            }
        });
        word4Button.setOnClickListener(new Button.OnClickListener() {
            boolean clicked = false;

            @Override
            public void onClick(View v) {

                if(!clicked) {
                    word4Button.setImageResource(R.drawable.rectangle_1);
                    clicked = true;
                    word4Pressed = true;
                }else{

                    word4Button.setImageResource(android.R.color.transparent);
                    clicked = false;
                    word4Pressed = false;
                }
            }
        });
        word5Button.setOnClickListener(new Button.OnClickListener() {
            boolean clicked = false;

            @Override
            public void onClick(View v) {

                if(!clicked) {
                    word5Button.setImageResource(R.drawable.rectangle_1);
                    clicked = true;
                    word5Pressed = true;
                }else{
                    word5Button.setImageResource(android.R.color.transparent);

                    clicked = false;
                    word5Pressed = false;
                }

            }
        });
        word6Button.setOnClickListener(new Button.OnClickListener() {
            boolean clicked = false;



            @Override
            public void onClick(View v) {


                if(!clicked) {
                    word6Button.setImageResource(R.drawable.rectangle_1);
                    clicked = true;
                    word6Pressed = true;
                }else{
                    word6Button.setImageResource(android.R.color.transparent);

                    clicked = false;
                    word6Pressed = false;
                }

            }
        });
        checkButton.setOnClickListener(new Button.OnClickListener() {


            @Override
            public void onClick(View v) {
                wordsAlreadyGenerated = false;
                correctWords += operateImages(word1Check,word1X,word1Button,word1Pressed,genWord1.isWordReal(),correctWords,getApplicationContext());
                correctWords += operateImages(word2Check,word2X,word2Button,word2Pressed,genWord2.isWordReal(),correctWords,getApplicationContext());
                correctWords += operateImages(word3Check,word3X,word3Button,word3Pressed,genWord3.isWordReal(),correctWords,getApplicationContext());
                correctWords += operateImages(word4Check,word4X,word4Button,word4Pressed,genWord4.isWordReal(),correctWords,getApplicationContext());
                correctWords += operateImages(word5Check,word5X,word5Button,word5Pressed,genWord5.isWordReal(),correctWords,getApplicationContext());
                correctWords += operateImages(word6Check,word6X,word6Button,word6Pressed,genWord6.isWordReal(),correctWords,getApplicationContext());
                CharSequence currentScore = "Score: " + correctWords + "/6";
                checkButton.setClickable(false);
                Context context = score.getContext();
                int id = context.getResources().getIdentifier("score__" + correctWords + "_6", "drawable", context.getPackageName());
                score.setImageResource(id);
                score.setVisibility(View.VISIBLE);
                nextButton.setVisibility(View.VISIBLE);

            }

        });

        nextButton.setOnClickListener(new Button.OnClickListener() {


            @Override
            public void onClick(View v) {
                wordsAlreadyGenerated = false;
                // Create a Intent:
                // (This object contains content that will be sent to Example1Activity).

                Intent myIntent = new Intent(SixWordGenerate.this, SixWordGenerate.class);
                // Parameter for Intent.


                // Start Example1Activity.
                SixWordGenerate.this.startActivity(myIntent);

            }

        });
        homeButtonSixWordGenerate.setOnClickListener(new Button.OnClickListener() {


            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(SixWordGenerate.this, MainActivity.class);
                // Parameter for Intent.


                // Start Example1Activity.
                SixWordGenerate.this.startActivity(myIntent);

            }

        });


    }
    @Override
    public void onBackPressed()  {


        Intent myIntent = new Intent(SixWordGenerate.this,MainActivity.class);
        SixWordGenerate.this.startActivity(myIntent);
    }

}