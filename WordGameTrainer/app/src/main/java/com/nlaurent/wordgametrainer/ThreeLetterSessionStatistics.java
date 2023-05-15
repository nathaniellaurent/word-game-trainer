package com.nlaurent.wordgametrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;



public class ThreeLetterSessionStatistics extends AppCompatActivity {

    private TextView correctGuessCount;
    private TextView incorrectGuessCount;
    private TextView correctGuessCountLegacy;
    private TextView incorrectGuessCountLegacy;
    private int correctGuesses;
    private  int incorrectGuesses;
    private int correctGuessesLegacy;
    private int incorrectGuessesLegacy;
    private CharSequence correctGuessesChar;
    private CharSequence incorrectGuessesChar;
    private CharSequence correctGuessesCharLegacy;
    private CharSequence incorrectGuessesCharLegacy;
    private ImageView correctGuessesBar;
    private ImageView incorrectGuessesBar;
    private ImageView correctGuessesBarLegacy;
    private ImageView incorrectGuessesBarLegacy;
    private ImageView sessionBox;
    private ImageView allTimeBox;
    private Button clearDataButton;
    private ImageButton homeButtonStats;
    private ImageButton sessionStatsButton;
    private ImageButton legacyStatsButton;
    private ImageButton twoLetterButton;
    private ImageView twoLetterBox;
    private boolean sessionButtonOn = true;

    protected static List<Integer> threeLetterScores = Arrays.asList(0,0);


    // Get the intent sent from MainActivity.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.statistics);
        correctGuesses = threeLetterScores.get(0);
        incorrectGuesses = threeLetterScores.get(1);
        this.correctGuessCount  = (TextView)this.findViewById(R.id.correctGuessCount);
        this.incorrectGuessCount  = (TextView)this.findViewById(R.id.incorrectGuessCount);
        this.correctGuessesBar = (ImageView) this.findViewById(R.id.correctGuesses);
        this.incorrectGuessesBar = (ImageView) this.findViewById(R.id.incorrectGuesses);
        this.clearDataButton = (Button) this.findViewById(R.id.clearDataButton);
        this.homeButtonStats = (ImageButton)this.findViewById(R.id.homeButtonStats);
        this.correctGuessCountLegacy  = (TextView)this.findViewById(R.id.correctGuessCountLegacy);
        this.incorrectGuessCountLegacy  = (TextView)this.findViewById(R.id.incorrectGuessCountLegacy);
        this.correctGuessesBarLegacy = (ImageView) this.findViewById(R.id.correctGuessesLegacy);
        this.incorrectGuessesBarLegacy = (ImageView) this.findViewById(R.id.incorrectGuessesLegacy);
        this.sessionStatsButton = (ImageButton)this.findViewById(R.id.sessionStatsButton);
        this.legacyStatsButton = (ImageButton)this.findViewById(R.id.legacyStatsButton);
        this.sessionBox = (ImageView) this.findViewById(R.id.sessionBox);
        this.allTimeBox = (ImageView) this.findViewById(R.id.allTimeBox);
        this.twoLetterButton = (ImageButton) this.findViewById(R.id.twoLetterButton);
        this.twoLetterBox = (ImageView) this.findViewById(R.id.twoLetterBox);


        MainActivity mainActivity = new MainActivity();

        correctGuessesChar = Integer.toString(correctGuesses);
        incorrectGuessesChar = Integer.toString(incorrectGuesses);
        correctGuessCount.setText(correctGuessesChar);
        incorrectGuessCount.setText(incorrectGuessesChar);

        if(Arrays.asList(mainActivity.readFromFile("currentThreeLetterScore.txt",getApplicationContext()).split(", ")).size() == 1){
            mainActivity.writeToFile("currentThreeLetterScore.txt",Arrays.asList(0,0).toString(),getApplicationContext());
        }
        correctGuessesCharLegacy = Arrays.asList(mainActivity.readFromFile("currentThreeLetterScore.txt",getApplicationContext()).split(", ")).get(0).replace("[","");
        incorrectGuessesCharLegacy = Arrays.asList(mainActivity.readFromFile("currentThreeLetterScore.txt",getApplicationContext()).split(", ")).get(1).replace("]","");
        correctGuessCountLegacy.setText(correctGuessesCharLegacy);
        incorrectGuessCountLegacy.setText(incorrectGuessesCharLegacy);
        correctGuessesLegacy = Integer.parseInt(correctGuessesCharLegacy.toString());
        incorrectGuessesLegacy = Integer.parseInt(incorrectGuessesCharLegacy.toString());


        allTimeBox.setVisibility(View.INVISIBLE);
        twoLetterBox.setVisibility(View.INVISIBLE);

        if(correctGuesses+incorrectGuesses > 0) {
            correctGuessesBar.requestLayout();
            incorrectGuessesBar.requestLayout();
            correctGuessesBar.getLayoutParams().height = 500 * correctGuesses / (correctGuesses + incorrectGuesses);
            incorrectGuessesBar.getLayoutParams().height = 500 * incorrectGuesses / (correctGuesses + incorrectGuesses);
        }else{
            correctGuessesBar.requestLayout();
            incorrectGuessesBar.requestLayout();
            correctGuessesBar.getLayoutParams().height = 10;
            incorrectGuessesBar.getLayoutParams().height = 10;
        }
        if(correctGuessesLegacy+incorrectGuessesLegacy > 0) {
            correctGuessesBarLegacy.requestLayout();
            incorrectGuessesBarLegacy.requestLayout();
            correctGuessesBarLegacy.getLayoutParams().height = 500 * correctGuessesLegacy / (correctGuessesLegacy + incorrectGuessesLegacy);
            incorrectGuessesBarLegacy.getLayoutParams().height = 500 * incorrectGuessesLegacy / (correctGuessesLegacy + incorrectGuessesLegacy);
        }else{
            correctGuessesBarLegacy.requestLayout();
            incorrectGuessesBarLegacy.requestLayout();
            correctGuessesBarLegacy.getLayoutParams().height = 10;
            incorrectGuessesBarLegacy.getLayoutParams().height = 10;
        }

        clearDataButton.setOnClickListener(new Button.OnClickListener() {


            @Override
            public void onClick(View v) {
                if(sessionButtonOn) {
                    threeLetterScores = Arrays.asList(0, 0);
                    correctGuesses = threeLetterScores.get(0);
                    incorrectGuesses = threeLetterScores.get(1);
                    correctGuessesChar = Integer.toString(correctGuesses);
                    incorrectGuessesChar = Integer.toString(incorrectGuesses);
                    correctGuessCount.setText(correctGuessesChar);
                    incorrectGuessCount.setText(incorrectGuessesChar);
                    correctGuessesBar.requestLayout();
                    incorrectGuessesBar.requestLayout();
                    correctGuessesBar.getLayoutParams().height = 10;
                    incorrectGuessesBar.getLayoutParams().height = 10;
                }
                else{
                    mainActivity.writeToFile("currentThreeLetterScore.txt",Arrays.asList(0,0).toString(),getApplicationContext());
                    correctGuessesCharLegacy = Arrays.asList(mainActivity.readFromFile("currentThreeLetterScore.txt",getApplicationContext()).split(", ")).get(0).replace("[","");
                    incorrectGuessesCharLegacy = Arrays.asList(mainActivity.readFromFile("currentThreeLetterScore.txt",getApplicationContext()).split(", ")).get(1).replace("]","");
                    correctGuessCountLegacy.setText(correctGuessesCharLegacy);
                    incorrectGuessCountLegacy.setText(incorrectGuessesCharLegacy);
                    correctGuessesBarLegacy.requestLayout();
                    incorrectGuessesBarLegacy.requestLayout();
                    correctGuessesBarLegacy.getLayoutParams().height = 10;
                    incorrectGuessesBarLegacy.getLayoutParams().height = 10;
                }
            }
        });
        homeButtonStats.setOnClickListener(new Button.OnClickListener() {


            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(ThreeLetterSessionStatistics.this, MainActivity.class);
                // Parameter for Intent.


                // Start Example1Activity.
                ThreeLetterSessionStatistics.this.startActivity(myIntent);

            }

        });
        sessionStatsButton.setOnClickListener(new Button.OnClickListener() {


            @Override
            public void onClick(View v) {
                if(!sessionButtonOn){
                    sessionBox.setVisibility(View.VISIBLE);
                    allTimeBox.setVisibility(View.INVISIBLE);
                    correctGuessesBar.setVisibility(View.VISIBLE);
                    incorrectGuessesBar.setVisibility(View.VISIBLE);
                    correctGuessCount.setVisibility(View.VISIBLE);
                    incorrectGuessCount.setVisibility(View.VISIBLE);
                    correctGuessesBarLegacy.setVisibility(View.INVISIBLE);
                    incorrectGuessesBarLegacy.setVisibility(View.INVISIBLE);
                    correctGuessCountLegacy.setVisibility(View.INVISIBLE);
                    incorrectGuessCountLegacy.setVisibility(View.INVISIBLE);
                    sessionButtonOn = true;
                }
            }
        });
        legacyStatsButton.setOnClickListener(new Button.OnClickListener() {


            @Override
            public void onClick(View v) {
                if(sessionButtonOn){
                    sessionBox.setVisibility(View.INVISIBLE);
                    allTimeBox.setVisibility(View.VISIBLE);
                    correctGuessesBar.setVisibility(View.INVISIBLE);
                    incorrectGuessesBar.setVisibility(View.INVISIBLE);
                    correctGuessCount.setVisibility(View.INVISIBLE);
                    incorrectGuessCount.setVisibility(View.INVISIBLE);
                    correctGuessesBarLegacy.setVisibility(View.VISIBLE);
                    incorrectGuessesBarLegacy.setVisibility(View.VISIBLE);
                    correctGuessCountLegacy.setVisibility(View.VISIBLE);
                    incorrectGuessCountLegacy.setVisibility(View.VISIBLE);
                    sessionButtonOn = false;
                }
            }
        });
        twoLetterButton.setOnClickListener(new Button.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ThreeLetterSessionStatistics.this,SessionStatistics.class);
                ThreeLetterSessionStatistics.this.startActivity(myIntent);
                overridePendingTransition(0, 0);
            }
        });



    }
    @Override
    public void onBackPressed()  {


        Intent myIntent = new Intent(ThreeLetterSessionStatistics.this,MainActivity.class);
        ThreeLetterSessionStatistics.this.startActivity(myIntent);

    }
}