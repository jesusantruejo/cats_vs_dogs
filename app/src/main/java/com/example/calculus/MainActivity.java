package com.example.calculus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

public EditText name;
public Button higher;
public Button lower;
public ImageButton difficultyImage;
public ImageButton historialBoton;
public int difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConstraintLayout rootLayout = (ConstraintLayout) findViewById(R.id.root_layout_ini);
        AnimationDrawable animDrawable = (AnimationDrawable) rootLayout.getBackground();
        animDrawable.setEnterFadeDuration(10);
        animDrawable.setExitFadeDuration(5000);
        animDrawable.start();
        name=findViewById(R.id.editTextTextPersonName);
        Button play=findViewById(R.id.buttonPlay);

        play.setOnClickListener(this);


        higher=findViewById(R.id.buttonHigher);
        higher.setOnClickListener(this);
        lower=findViewById(R.id.buttonLower);
        lower.setOnClickListener(this);
        difficultyImage=findViewById(R.id.imageButtonDifficulty);
        difficulty=1;
        difficultyInImages();
        Intent svc=new Intent(this, SongService.class);
        startService(svc);

    }

    @Override
    protected void onPause() {
        Intent svc=new Intent(this, SongService.class);
        stopService(svc);
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent svc=new Intent(this, SongService.class);
        startService(svc);

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("difficulty",difficulty);
        savedInstanceState.putString("nombre",name.getText().toString());
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        difficulty=savedInstanceState.getInt("difficulty");
        name.setText(savedInstanceState.getString("nombre")+"");
        difficultyInImages();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.buttonPlay){

        if(!name.getText().toString().equals("")){
            Intent myIntent = new Intent(MainActivity.this, GameScreen.class);
            myIntent.putExtra("nombre",name.getText().toString());
            myIntent.putExtra("dificultad",difficulty);
            MainActivity.this.startActivity(myIntent);
        }
        }

        if(v.getId()==R.id.buttonHigher){
            if(difficulty<3){
                difficulty++;
                difficultyInImages();
            }
        }
        if(v.getId()==R.id.buttonLower){
            if(difficulty>1){
               difficulty--;
               difficultyInImages();
            }
        }
        if(v.getId()==R.id.imageButtonDifficulty){

        }
    }

    public void difficultyInImages() {
        switch (difficulty) {
            case 1:
                difficultyImage.setImageResource(R.drawable.ez);
                break;

            case 2:
                difficultyImage.setImageResource(R.drawable.mid);
                break;

            case 3:
                difficultyImage.setImageResource(R.drawable.hard);
                break;
        }

    }

}

