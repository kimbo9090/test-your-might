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
    private static final String TAG = "ListadoPreguntas";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myContext= listadoPreguntas.this;
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

                    bundle.putInt("Codigo",items.get(position).getCodigo());

                    editintent.putExtras(bundle);

                    startActivity(editintent);




                    if (direction == ItemTouchHelper.RIGHT) { //if swipe right

                        myLog.d("deslizando a la derecha","jeje");

                    }
                }
            }

        };


        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(simpleCallback);
        itemTouchhelper.attachToRecyclerView(recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myLog.d(TAG, "Finalizando OnResume");
    }


}
