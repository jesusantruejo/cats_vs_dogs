package com.example.calculus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class History extends AppCompatActivity {
    public ArrayList historial;
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ConstraintLayout rootLayout = (ConstraintLayout) findViewById(R.id.root_layout_history);
        AnimationDrawable animDrawable = (AnimationDrawable) rootLayout.getBackground();
        animDrawable.setEnterFadeDuration(10);
        animDrawable.setExitFadeDuration(5000);
        animDrawable.start();
        historial= (ArrayList<Object>) getIntent().getSerializableExtra("historial");
        lista = findViewById(R.id.listaView);
        refreshList();
    }

    public void refreshList(){
        String output = "";
        ArrayList texto= new ArrayList<String>();
        for (Object p : historial) {
            texto.add(p.toString());
        }
        
        ArrayAdapter<String> aTexto = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, texto);


        lista.setAdapter(aTexto);


        System.out.println("" + output);
    }
}