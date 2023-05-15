package com.nlaurent.wordgametrainer;

import java.util.ArrayList;

public class Word {

    String word;
    ArrayList<String> definitions;

    String getWord(){
        return this.word;
    }
    ArrayList<String> getDefinitions(){
        return this.definitions;
    }
    void setWord(String word){
        this.word = word;
    }
    void setDefinitions(ArrayList<String> definitions){
        this.definitions = definitions;
    }

}
