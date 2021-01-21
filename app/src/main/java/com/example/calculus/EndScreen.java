package com.example.calculus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class EndScreen extends AppCompatActivity implements View.OnClickListener {

    public TextView resultado;
    public TextView stats;
    public Button volver;
    public Button intentar;
    public String nombre;
    public int difficulty;
    public String resultadoText;
    public ImageView cute;
    public int puntos;
    public int vidas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);
        ConstraintLayout rootLayout = (ConstraintLayout) findViewById(R.id.root_layout_end);
        AnimationDrawable animDrawable = (AnimationDrawable) rootLayout.getBackground();
        animDrawable.setEnterFadeDuration(10);
        animDrawable.setExitFadeDuration(5000);
        animDrawable.start();

        resultado=findViewById(R.id.textViewResultado);
        stats=findViewById(R.id.textViewStats);
        volver=findViewById(R.id.buttonMainScreen);
        volver.setOnClickListener(this);
        intentar=findViewById(R.id.buttonTryOrPass);
        intentar.setOnClickListener(this);
        nombre=getIntent().getExtras().getString("nombre");
        difficulty=getIntent().getExtras().getInt("dificultad");
        resultadoText=getIntent().getExtras().getString("tipo");
        vidas=getIntent().getExtras().getInt("vidas");
        puntos=getIntent().getExtras().getInt("puntos");
        cute=findViewById(R.id.imageViewCute);
       refrescarDatos();
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
        // Save the user's current game state
        savedInstanceState.putInt("difficulty",difficulty);
        savedInstanceState.putInt("vidas",vidas);
        savedInstanceState.putInt("puntos",puntos);
        savedInstanceState.putString("nombre",nombre);
        savedInstanceState.putString("tipo",resultadoText);
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);


        difficulty=savedInstanceState.getInt("difficulty");
        vidas=savedInstanceState.getInt("vidas");
        puntos=savedInstanceState.getInt("puntos");
        nombre=savedInstanceState.getString("nombre");
        resultadoText=savedInstanceState.getString("tipo");


    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.buttonMainScreen){

            Intent i = new Intent(EndScreen.this,MainActivity.class);

            startActivity(i);
        }
        if(v.getId()==R.id.buttonTryOrPass){


          if(resultadoText.equals("GANADO")){
              Intent myIntent = new Intent(EndScreen.this, GameScreen.class);

              myIntent.putExtra("nombre",nombre);
              myIntent.putExtra("dificultad",difficulty+1);
              EndScreen.this.startActivity(myIntent);
              }
          else{
              Intent myIntent = new Intent(EndScreen.this, GameScreen.class);

              myIntent.putExtra("nombre",nombre);
              myIntent.putExtra("dificultad",difficulty);
              EndScreen.this.startActivity(myIntent);
          }

        }

    }

    public void refrescarDatos(){
        resultado.setText("¡Has "+getIntent().getExtras().getString("tipo")+", "+nombre+"!");
        stats.setText("PUNTOS: "+puntos+"  VIDAS SALVADAS: "+vidas);
        if(difficulty<=3){
            if(difficulty==3 && resultadoText.equals("GANADO")){
                intentar.setText("HAS COMPLETADO EL JUEGO");
                intentar.setEnabled(false);
                cute.setImageResource(R.drawable.hard);
            }

            else if(resultadoText.equals("GANADO")){
                intentar.setText("¡Hazlo más difícil!");
                cute.setImageResource(R.drawable.ctwo);
            }
            else{
                intentar.setText("¡Quiero probar otra vez!");
                cute.setImageResource(R.drawable.sad);
            }
        }



    }



}