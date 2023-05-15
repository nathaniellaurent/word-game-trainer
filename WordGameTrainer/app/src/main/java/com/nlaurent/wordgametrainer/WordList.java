package com.nlaurent.wordgametrainer;

import java.util.ArrayList;

public class WordList {

    ArrayList<Word> wordList;

    ArrayList<Word> getWordList(){
        return this.wordList;
    }

    void setWordList(ArrayList<Word> wordlist){
        this.wordList = wordlist;
    }
}
