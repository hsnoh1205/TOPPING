package com.example.hsnoh.topping;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MAIN extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton wordButton = (ImageButton) findViewById(R.id.wordButton);
        wordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), word.class);
                startActivityForResult(intent, 0);
            }
        });
        ImageButton sentenceButton = (ImageButton) findViewById(R.id.sentenceButton);
        sentenceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), sentence.class);
                startActivityForResult(intent, 0);
            }
        });
        ImageButton gameButton = (ImageButton) findViewById(R.id.gameButton);
        gameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), game.class);
                startActivityForResult(intent, 0);
            }
        });
        ImageButton listeningButton = (ImageButton) findViewById(R.id.listeningButton);
        listeningButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), listening.class);
                startActivityForResult(intent, 0);
            }
        });
    }
}
