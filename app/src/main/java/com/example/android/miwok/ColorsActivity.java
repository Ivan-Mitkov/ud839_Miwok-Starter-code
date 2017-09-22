package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private MediaPlayer.OnCompletionListener onComplet= new MediaPlayer.OnCompletionListener(){

        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word( "red","weṭeṭṭi",R.drawable.color_red,R.raw.color_red));
        words.add(new Word( "green","chokokki",R.drawable.color_green,R.raw.color_green));
        words.add(new Word( "brown","ṭakaakki",R.drawable.color_brown,R.raw.color_brown));
        words.add(new Word( "gray","ṭopoppi",R.drawable.color_gray,R.raw.color_gray));
        words.add(new Word( "black","kululli",R.drawable.color_black,R.raw.color_black));
        words.add(new Word( "white","kelelli",R.drawable.color_white,R.raw.color_white));
        words.add(new Word( "dusty yellow","ṭopiisә",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        words.add(new Word( "mustard yellow","chiwiiṭә",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));




        WordAdapter itemsAdapter = new WordAdapter(this, words,R.color.category_colors);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ColorsActivity.this,
                        "play word",
                        Toast.LENGTH_SHORT)
                        .show();
                Word a= words.get(position);

                mediaPlayer= MediaPlayer.create(ColorsActivity.this, a.getmAudio());
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(onComplet);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
        }
    }
}
