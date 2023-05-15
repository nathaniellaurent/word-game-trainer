package com.nlaurent.wordgametrainer;

import java.util.ArrayList;
import java.util.Random;

public class ThreeLetterOperations {


    private int amountNotEqual = 0;



    public static class GeneratedWord
    {
        protected String currentWord;
        protected boolean wordReal;
        protected String currentDefinition;

        public String getCurrentWord() {
            return currentWord;
        }

        public void setCurrentWord(String currentWord) {
            this.currentWord = currentWord;
        }

        public boolean isWordReal() {
            return wordReal;
        }

        public void setWordReal(boolean wordReal) {
            this.wordReal = wordReal;
        }
    }



    public GeneratedWord generateWord()
    {

        Random random = new Random();
        int randomInt = random.nextInt(MainActivity.myWords.wordList.size());
        Word randomWord = MainActivity.myWords.wordList.get(randomInt);
        ArrayList<String> definitionArray = randomWord.getDefinitions();
        int failedOn = -1;
        GeneratedWord genWord = new GeneratedWord();
        Random rand = new Random();
        String currentWord = new String();
        boolean isWordReal = false;
        String currentDefinition = new String();
        int binary = rand.nextInt(2);
        if(binary == 1){
            isWordReal = true;
            currentWord = randomWord.getWord();
            for(int i = 0; i < definitionArray.size();i++){
                currentDefinition = currentDefinition + definitionArray.get(i);
            }
        }
        if(binary == 0){
            isWordReal = false;
            boolean wordFail = true;
            int rand3 = rand.nextInt(3);
            String allConsonants = "BCDFGHJKLMNPQRSTVWXYZ";
            String allVowels = "AEIOU";
            int attempts = 0;
            while(wordFail && attempts < 10) {
                char randomChar;
                if (Character.toString(randomWord.getWord().charAt(1)).equals("A") || Character.toString(randomWord.getWord().charAt(1)).equals("E") || Character.toString(randomWord.getWord().charAt(1)).equals("I") || Character.toString(randomWord.getWord().charAt(1)).equals("O") || Character.toString(randomWord.getWord().charAt(1)).equals("U")) {
                    randomChar = allVowels.charAt(rand.nextInt(5));
                } else {
                    randomChar = allConsonants.charAt(rand.nextInt(21));
                }

                switch (rand3) {
                    case 0:
                        currentWord = Character.toString(randomChar) + Character.toString(randomWord.getWord().charAt(1)) + Character.toString(randomWord.getWord().charAt(2));
                        break;
                    case 1:
                        currentWord = Character.toString(randomWord.getWord().charAt(0)) + Character.toString(randomChar) + Character.toString(randomWord.getWord().charAt(2));
                        break;
                    case 2:
                        currentWord = Character.toString(randomWord.getWord().charAt(0)) + Character.toString(randomWord.getWord().charAt(1)) + Character.toString(randomChar);
                        break;
                }
                wordFail = false;

                for(int i = 0; i < MainActivity.myWords.wordList.size(); i++){
                    if(currentWord.equals(MainActivity.myWords.wordList.get(i).getWord())){
                        wordFail = true;
                        failedOn = i;
                    }
                }
                attempts++;
                if(attempts == 10 && wordFail){
                    isWordReal = true;
                    currentWord = randomWord.getWord();
                    for(int i = 0; i < definitionArray.size();i++){
                        currentDefinition = currentDefinition + definitionArray.get(i);
                    }
                }
                System.out.println("i");

            }

        }
        genWord.currentWord=currentWord;
        genWord.wordReal=isWordReal;
        genWord.currentDefinition = currentDefinition;


        return genWord;
    }
}
