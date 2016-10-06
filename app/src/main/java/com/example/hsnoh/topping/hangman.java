package com.example.hsnoh.topping;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


class randomWord {
    public final int lengthOfWord;
    private String word;

    public randomWord() {
        word = createWord();
        lengthOfWord = word.length();
    }

    private String createWord() {
        word = "mobile";//TODO random word create
        return word;
    }

    public String getWord() {
        return word;
    }

    public boolean isLetterInWord(char letter) {
        return word.indexOf(letter) >= 0;
    }

    @Override
    public boolean equals(Object other) {
        return word.equals(other);
    }

    public char charAt(int index) {
        return word.charAt(index);
    }

}

class tried extends AppCompatActivity {
    static final int MAXFAIL = 6;
    private char[] notValidLetter;
    private int failCount;
    private String stringForDisplay;
    private boolean[] isTried;
    private int hangmanForDisplay;

    public tried(int maxTryNumber) {
        notValidLetter = new char[maxTryNumber];
        isTried = new boolean[26];
        for (int i = 0; i < 26; i++) isTried[i] = false;
        failCount = 0;
        stringForDisplay = "";
        hangmanForDisplay = R.drawable.hangman0;
    }

    public void increaseFailCount() {
        failCount++;
        return;
    }

    public void storeNotValidLetter(char guessLetter) {
        int index = guessLetter - 'a';
        notValidLetter[failCount] = guessLetter;
        isTried[index] = true;
        return;
    }

    public boolean isTriedBefore(char guessLetter) {
        int index = guessLetter - 'a';
        return isTried[index];
    }

    public boolean setTried(char guessLetter) {
        int index = guessLetter - 'a';
        return isTried[index] = true;
    }

    public boolean isGameOver() {
        return failCount == MAXFAIL;
    }

    public void displayTriedCharAt(TextView tried) {
        stringForDisplay = "";
        for (int i = 0; i < failCount; i++) {
            stringForDisplay += notValidLetter[i];
            stringForDisplay += ", ";
        }
        tried.setText("Tried : " + stringForDisplay);
    }

    public void displayHangmanAt(ImageView hangman) {

        switch (failCount) {
            case 1:
                hangmanForDisplay = R.drawable.hangman1;
                break;
            case 2:
                hangmanForDisplay = R.drawable.hangman2;
                break;
            case 3:
                hangmanForDisplay = R.drawable.hangman3;
                break;
            case 4:
                hangmanForDisplay = R.drawable.hangman4;
                break;
            case 5:
                hangmanForDisplay = R.drawable.hangman5;
                break;
            case 6:
                hangmanForDisplay = R.drawable.hangman6;
                break;

        }
        hangman.setImageResource(hangmanForDisplay);
    }

}

class blank {

    private char[] blank;
    private int remainedBlank;
    private String answerWord;
    private String stringForDisplay;

    public blank(String word) {
        answerWord = word;
        blank = new char[word.length()];
        for (int i = 0; i < word.length(); i++) blank[i] = '-';
        remainedBlank = word.length();
        stringForDisplay = "";
    }

    public void fillBlank(char letter) {
        for (int i = 0; i < answerWord.length(); i++) {
            if (answerWord.charAt(i) == letter) blank[i] = letter;
        }
    }

    public void decreaseRemainedBlank() {
        remainedBlank--;
    }

    public void displayCurrentStatusAt(TextView CurrentStatus) {
        stringForDisplay = "";
        for (int i = 0; i < answerWord.length(); i++) {
            stringForDisplay += blank[i];
            stringForDisplay += " ";
        }
        CurrentStatus.setText(stringForDisplay);
    }

    public boolean isClear() {
        return remainedBlank == 0;
    }
}

public class hangman extends AppCompatActivity {

    public randomWord answerWord;
    public blank blank;
    public tried tried;
    public TextView currentStatus;
    public ImageView hangman;
    public TextView triedTextView;
    public TextView errormessageTextView;
    public TextView hintTextView;
    public EditText guess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman);
        currentStatus = (TextView) findViewById(R.id.currentStatus);
        hangman = (ImageView) findViewById(R.id.hangman);
        triedTextView = (TextView) findViewById(R.id.triedTextView);
        errormessageTextView = (TextView) findViewById(R.id.errormessageTextView);
        hintTextView = (TextView) findViewById(R.id.hintTextView);
        guess = (EditText) findViewById(R.id.guess);
        answerWord = new randomWord();
        blank = new blank(answerWord.getWord());
        tried = new tried(answerWord.lengthOfWord);
    }

    public boolean isVow(char foo) {
        final String vow = "aeoiu";
        for (int i = 0; i < vow.length(); i++)
            if (vow.charAt(i) == foo) return true;
        return false;
    }

    public void hint(View view) {
        int vowCount = 0;
        int consCount = 0;
        for (int i = 0; i < answerWord.lengthOfWord; i++) {
            if (isVow(answerWord.charAt(i))) vowCount++;
            else consCount++;
        }
        hintTextView.setText("HINT\nnumber of vow : " + vowCount + "\nnumber of cons : " + consCount);

    }

    public void display() {
        blank.displayCurrentStatusAt(currentStatus);
        tried.displayTriedCharAt(triedTextView);
        tried.displayHangmanAt(hangman);
        guess.setText("");
    }

    public void guessWord(View view) {//TODO more modulation
        char guessLetter = ' ';
        String temp2 = guess.getText().toString();
        String temp = "";
        if (temp2.toUpperCase().equals(temp2.toLowerCase())) {
            errormessageTextView.setText("It is not alphabet or didn't input alphabet");
            return;
        } else temp2 = temp2.toLowerCase();

        if (temp2.equals(null)) return;
        else guessLetter = temp2.charAt(0);
        //TODO is guessed char alphabetic character?

        if (tried.isTriedBefore(guessLetter)) {
            errormessageTextView.setText(guessLetter + " is already `Tried");
        } else {
            if (answerWord.isLetterInWord(guessLetter)) {
                blank.fillBlank(guessLetter);
                blank.decreaseRemainedBlank();
            } else {
                tried.storeNotValidLetter(guessLetter);
                tried.increaseFailCount();
            }
            errormessageTextView.setText("");
            tried.setTried(guessLetter);
        }
        display();
        if (blank.isClear()) {
        } else if (tried.isGameOver()) {
        }//TODO dialog regame or quit
    }
}