package com.example.calculus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class GameScreen extends AppCompatActivity implements View.OnClickListener {

    public Level currentLevel;
    public int currentCuenta;
    public ImageButton a;
    public ImageButton b;
    public ImageButton plus;
    public ImageButton minus;
    public ImageButton newOnes;
    public ImageButton clarify;
    public ImageButton comodines;
    public ImageButton corazones;
    public TextView operator;
    public TextView showA;
    public TextView showB;
    public TextView points;
    public TextView lives;
    public TextView nombreShow;
    public EditText input;
    public Button tryResult;
    public int pointsN;
    public int livesN;
    public int difficulty;
    public String nombre;
    public MediaPlayer dog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        ConstraintLayout rootLayout = (ConstraintLayout) findViewById(R.id.root_layout);
        AnimationDrawable animDrawable = (AnimationDrawable) rootLayout.getBackground();
        animDrawable.setEnterFadeDuration(10);
        animDrawable.setExitFadeDuration(5000);
        animDrawable.start();

        pointsN=0;
        a=findViewById(R.id.textViewA);
        a.setOnClickListener(this);

        b=findViewById(R.id.textViewB);
        b.setOnClickListener(this);
        operator=findViewById(R.id.textViewOperator);
        showA=findViewById(R.id.textViewShowA);
        showB=findViewById(R.id.textViewShowB);
        points=findViewById(R.id.textViewPoints);
        lives=findViewById(R.id.textViewLives);
        input=findViewById(R.id.resultInput);
        tryResult=findViewById(R.id.buttonTry);
        tryResult.setOnClickListener(this);
        plus=findViewById(R.id.imageButtonPlus);
        minus=findViewById(R.id.imageButtonMinus);
        newOnes=findViewById(R.id.imageButtonNewOne);
        clarify=findViewById(R.id.imageButtonClarify);
        comodines=findViewById(R.id.imageViewComodines);
        plus.setOnClickListener(this);
        minus.setOnClickListener(this);
        newOnes.setOnClickListener(this);
        clarify.setOnClickListener(this);
        showA=findViewById(R.id.textViewShowA);
        showB=findViewById(R.id.textViewShowB);
        nombreShow=findViewById(R.id.textViewNombre);
        corazones=findViewById(R.id.imageButtonCorazones);
        difficulty=getIntent().getExtras().getInt("dificultad");
        nombre=getIntent().getExtras().getString("nombre");
        currentLevel=new Level(difficulty);
        nombreShow.setText(nombre);
        livesN=4-difficulty;
        lives.setText("Vidas: "+livesN);
        points.setText("Puntos: "+0);
        currentCuenta=0;
          refreshCuenta();
        comodinesImages();
        vidasImages();
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
        savedInstanceState.putInt("points", pointsN);
        savedInstanceState.putInt("lives", livesN);
        savedInstanceState.putInt("sublevel", currentCuenta);
        savedInstanceState.putInt("comodines", currentLevel.getComodines());
        savedInstanceState.putString("showa",showA.getText().toString());
        savedInstanceState.putString("showb",showB.getText().toString());
        savedInstanceState.putString("nombre",nombre);
        savedInstanceState.putParcelableArrayList("operaciones", (ArrayList<? extends Parcelable>) currentLevel.getCuentasNeutras());
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        difficulty=savedInstanceState.getInt("difficulty");
        currentLevel=new Level(difficulty);
        currentLevel.setComodines(savedInstanceState.getInt("comodines"));
        currentLevel.setCuentasNeutral(savedInstanceState.getParcelableArrayList("operaciones"));
        pointsN=savedInstanceState.getInt("points");
        livesN=savedInstanceState.getInt("lives");
        currentCuenta=savedInstanceState.getInt("sublevel");
        refreshCuenta();
        comodinesImages();
        showA.setText(savedInstanceState.getString("showa"));
        showB.setText(savedInstanceState.getString("showb"));
        nombreShow.setText(savedInstanceState.getString("nombre"));
        vidasImages();

    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.buttonTry){

            if(!input.getText().toString().equals("")){
                if(currentCuenta<5){
                    if(!currentLevel.getCuentas().get(currentCuenta).checkAnswer(Integer.parseInt(input.getText().toString()))){
                        livesN--;
                        lives.setText("Vidas: "+livesN);
                        vidasImages();
                    }
                    currentLevel.prueba(Integer.parseInt(input.getText().toString()));
                    points.setText("Puntos: "+currentLevel.getPuntuacionSobreSeis());
                    pointsN=currentLevel.getPuntuacionSobreSeis();

                    if(livesN==0){
                        finDePartida(false);
                    }
                    currentCuenta++;
                    refreshCuenta();
                    input.setText("");

                }
                else{
                    finDePartida(true);
                }
            }

         }

        if(v.getId()==R.id.imageButtonMinus){
            if(Integer.parseInt(input.getText().toString())>0){
                input.setText( (Integer.parseInt(input.getText().toString())*-1)+"");
            }

        }
        if(v.getId()==R.id.imageButtonPlus){

            if(Integer.parseInt(input.getText().toString())<0){
                input.setText( (Integer.parseInt(input.getText().toString())*-1)+"");
            }
        }
        if(v.getId()==R.id.imageButtonClarify){
            gastarComodin("clarify");
        }
        if(v.getId()==R.id.imageButtonNewOne){
            gastarComodin("newones");
        }
        if(v.getId()==R.id.textViewA){
            try {
                doggos(currentLevel.getCuentas().get(currentCuenta).getA());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(v.getId()==R.id.textViewB){
            try {
                kitties(currentLevel.getCuentas().get(currentCuenta).getB());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void finDePartida(boolean ganado){
        String nombre=getIntent().getExtras().getString("nombre");
        Intent i = new Intent(GameScreen.this,EndScreen.class);
        i.putExtra("puntos",pointsN);
        i.putExtra("vidas",livesN);
        i.putExtra("dificultad",difficulty);
        String stats="Partida "+ Calendar.getInstance().getTime().toString()+" \n Puntos: "+pointsN+"  Vidas restantes: "
                +livesN+ "  Comodines restantes: "+currentLevel.getComodines();

        if(ganado){
            i.putExtra("tipo","GANADO");
        }
        else{
            i.putExtra("tipo","PERDIDO");
        }
        i.putExtra("nombre",nombre);
        startActivity(i);
    }

    public void refreshCuenta(){
        if(currentLevel.getCuentas().get(currentCuenta).getOperator().equals("*")){
            operator.setText("X");
        }
        else{
            operator.setText(currentLevel.getCuentas().get(currentCuenta).getOperator());
        }

        assignCuties(currentLevel.getCuentas().get(currentCuenta));
        showA.setText("");
        showB.setText("");
    }

    public void gastarComodin(String tipo){

        if(currentLevel.getComodines()>0){
            if(tipo.equals("newones")){
                Generator input = new Generator(difficulty);
                currentLevel.getCuentas().set(currentCuenta,input);
                refreshCuenta();

            }
            if(tipo.equals("clarify")){
                showA.setText(""+currentLevel.getCuentas().get(currentCuenta).getA());
                showB.setText(""+currentLevel.getCuentas().get(currentCuenta).getB());
            }
            currentLevel.useComodin();
            comodinesImages();
        }

    }

    public void comodinesImages() {
        switch (currentLevel.getComodines()) {
            case 1:
                comodines.setImageResource(R.drawable.onecoin);
                   break;

            case 2:
                comodines.setImageResource(R.drawable.twocoin);
                break;

            case 3:
               comodines.setImageResource(R.drawable.threecoin);
                break;

            case 0:
                comodines.setImageResource(R.drawable.nothingness);
                break;

        }
    }

    public void vidasImages() {
        switch (livesN) {
            case 1:
                corazones.setImageResource(R.drawable.oneheart);
                break;

            case 2:
                corazones.setImageResource(R.drawable.twoheart);
                break;

            case 3:
                corazones.setImageResource(R.drawable.threeheart);
                break;

            case 0:
                corazones.setImageResource(R.drawable.nothingness);
                break;

        }
    }


    public void assignCuties(Generator g){

        b.setImageResource(R.drawable.seven);

        int numa=g.getA();
        int numb=g.getB();

        switch(numa)
        {
            case 1 :
                a.setImageResource(R.drawable.one);
                break;

            case 2 :
                a.setImageResource(R.drawable.two);
                break;

                case 3 :
                    a.setImageResource(R.drawable.three);
                break;

            case 4 :
                a.setImageResource(R.drawable.four);
                break;

            case 5 :
                a.setImageResource(R.drawable.five);
                break;

            case 6 :
                a.setImageResource(R.drawable.six);
                break;

            case 7 :
                a.setImageResource(R.drawable.seven);
                break;

            case 8 :
                a.setImageResource(R.drawable.eight);
                break;

            case 9 :
                a.setImageResource(R.drawable.nine);
                break;

        }

        switch(numb)
        {
            case 1 :
                b.setImageResource(R.drawable.cone);
                break;

            case 2 :
                b.setImageResource(R.drawable.ctwo);
                break;

            case 3 :
                b.setImageResource(R.drawable.cthree);
                break;

            case 4 :
                b.setImageResource(R.drawable.cfour);
                break;

            case 5 :
                b.setImageResource(R.drawable.cfive);
                break;

            case 6 :
                b.setImageResource(R.drawable.csix);
                break;

            case 7 :
                b.setImageResource(R.drawable.cseven);
                break;

            case 8 :
                b.setImageResource(R.drawable.ceight);
                break;

            case 9 :
                b.setImageResource(R.drawable.cnine);
                break;

        }

    }

    public void doggos(int number) throws InterruptedException {
        int index=number;

        Generator hola= new Generator(1);


        while(number>0){
            int elegido=hola.getRandomNumberInRange(1,3);
            if(elegido==1){
                dog = new MediaPlayer();
                dog = MediaPlayer.create(this,R.raw.done );
                dog.setVolume(60, 60);
                dog.start();
                while(dog.isPlaying()){
                    Thread.sleep(100);
                }
                dog.stop();
            }
            if(elegido==2){
                dog = new MediaPlayer();
                dog = MediaPlayer.create(this,R.raw.dtwo );
                dog.setVolume(60, 60);
                dog.start();
                while(dog.isPlaying()){
                    Thread.sleep(100);
                }
                dog.stop();
            }
            if(elegido==3){
                dog = new MediaPlayer();
                dog = MediaPlayer.create(this,R.raw.dthree );
                dog.setVolume(60, 60);
                dog.start();
                while(dog.isPlaying()){
                    Thread.sleep(100);
                }
                dog.stop();
            }
            number--;
        }

    }

    public void kitties(int number) throws InterruptedException {
        int index=number;
        Generator hola= new Generator(1);
        while(number>0){
            int elegido=hola.getRandomNumberInRange(1,3);
            if(elegido==1){
                dog = new MediaPlayer();
                dog = MediaPlayer.create(this,R.raw.kone );
                dog.setVolume(60, 60);
                dog.start();
                while(dog.isPlaying()){
                    Thread.sleep(100);
                }
                dog.stop();
            }
            if(elegido==2){
                dog = new MediaPlayer();
                dog = MediaPlayer.create(this,R.raw.ktwo );
                dog.setVolume(60, 60);
                dog.start();
                while(dog.isPlaying()){
                    Thread.sleep(100);
                }
                dog.stop();
            }
            if(elegido==3){
                dog = new MediaPlayer();
                dog = MediaPlayer.create(this,R.raw.kthree );
                dog.setVolume(60, 60);
                dog.start();
                while(dog.isPlaying()){
                    Thread.sleep(100);
                }
                dog.stop();
            }
            number--;
        }

    }





}

