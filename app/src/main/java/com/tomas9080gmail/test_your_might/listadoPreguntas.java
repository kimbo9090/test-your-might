package com.tomas9080gmail.test_your_might;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import com.tomas9080gmail.test_your_might.tareas.Pregunta;
import com.tomas9080gmail.test_your_might.tareas.adapter;

public class listadoPreguntas extends AppCompatActivity {
    private Context myContext;
    private ArrayList<Pregunta> items;
    private ArrayList<Pregunta> items2;
    private static final String TAG = "ListadoPreguntas";
    private TextView textView;

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        myContext = listadoPreguntas.this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_preguntas);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Textview de no hay pregunta
        textView = (TextView) findViewById(R.id.textView3);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(listadoPreguntas.this, anadirTarea.class);
                listadoPreguntas.this.startActivity(myIntent);
            }
        });



        myLog.d(TAG, "Finalizando OnCreate");

    }

    @Override
    protected void onResume(){
        myLog.d(TAG,"Iniciando on resume");
        super.onResume();
       items = new ArrayList<>();
       Repositorio.damePreguntas(myContext);

       items = Repositorio.getMisPreguntas();

       if (!items.isEmpty()){
           textView.setVisibility(View.INVISIBLE);

           final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.listado);

           recyclerView.setHasFixedSize(true);

           adapter adapter = new adapter(items);
           adapter.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   int position = recyclerView.getChildAdapterPosition(view);

                   Intent editintent = new Intent(listadoPreguntas.this, anadirTarea.class);

                   System.out.println("Codigo que va a petar porque intento eliminar el ultimo");
                   int miPrima2 = items.get(position).getCodigo();

                   System.out.println(items.get(position).getCodigo());
                   editintent.putExtra("codigo", items.get(position).getCodigo());

                    int miPrima = items.get(position).getCodigo();
                   startActivity(editintent);

               }
           });
           adapter.setOnLongListener(new View.OnLongClickListener() {
               @Override
               public boolean onLongClick(View view) {
                   System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEO AQUI");
                   int position = recyclerView.getChildAdapterPosition(view);
                   int esto = items.get(position).getCodigo();
                   System.out.println(esto);
                   return true;
               }
           });
           recyclerView.setAdapter(adapter);

           recyclerView.setLayoutManager(new LinearLayoutManager(this));
       }else{
           textView.setVisibility(View.VISIBLE);
       }
       myLog.d(TAG,"FInalizando on reusme");
    }

    @Override
    protected void onPause(
    ) {
        myLog.d(TAG, "Iniciando OnPause");
        super.onPause();
        myLog.d(TAG, "Finalizando OnPause");
    }
    @Override
    protected void onStop() {
        myLog.d(TAG, "Iniciando OnStop");
        super.onStop();
        myLog.d(TAG, "Finalizando OnStop");
    }
    @Override
    protected void onRestart() {
        myLog.d(TAG, "Iniciando OnRestart");
        super.onRestart();
        myLog.d(TAG, "Finalizando OnRestart");
    }
    @Override
    protected void onDestroy() {
        myLog.d(TAG, "Iniciando OnDestroy");
        super.onDestroy();
        myLog.d(TAG, "Finalizando OnDestroy");
    }
}
