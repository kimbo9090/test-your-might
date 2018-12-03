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
import java.util.ArrayList;
import java.util.Collections;

import com.tomas9080gmail.test_your_might.tareas.Pregunta;

public class listadoPreguntas extends AppCompatActivity {
    private Context myContext;
    private ArrayList<Pregunta> items;
    private Repositorio miRepo = new Repositorio();
    private static final String TAG = "ListadoPreguntas";

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_preguntas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
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
    protected void onResume() {
        myContext = listadoPreguntas.this;
        myLog.d(TAG, "Iniciando OnResume");
        super.onResume();
        items = new ArrayList<>();
        //miRepo.insertaPregunta(myContext);



        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.listado);
        recyclerView.setHasFixedSize(true);

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition(); //get position which is swipe

                if (direction == ItemTouchHelper.LEFT) { //if swipe left


                    Intent editintent = new Intent(myContext, anadirTarea.class);

                    Bundle bundle = new Bundle();

                    bundle.putInt("Codigo", items.get(position).getCodigo());

                    editintent.putExtras(bundle);

                    startActivity(editintent);


                    if (direction == ItemTouchHelper.RIGHT) { //if swipe right

                        myLog.d("deslizando a la derecha", "jeje");

                    }
                }
            }

        };


        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(simpleCallback);
        itemTouchhelper.attachToRecyclerView(recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myLog.d(TAG, "Finalizando OnResume");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
