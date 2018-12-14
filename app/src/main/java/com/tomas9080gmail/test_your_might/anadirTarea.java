package com.tomas9080gmail.test_your_might;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.TextView;

import com.tomas9080gmail.test_your_might.tareas.Pregunta;

import java.util.ArrayList;

public class anadirTarea extends AppCompatActivity {
    final private int CODE_WRITE_EXTERNAL_STORAGE_PERMISSION = 123;
    private Context context;
    private ConstraintLayout constraint;
    private Spinner miSpinnerCategoria;
    private int codigoPregunta = -1;
    Repositorio miRepo = new Repositorio();



    public int getCodigoPregunta() {
        return codigoPregunta;
    }

    public void setCodigoPregunta(int codigoPregunta) {
        this.codigoPregunta = codigoPregunta;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_anadir_tarea);
        super.onCreate(savedInstanceState);
        constraint = findViewById(R.id.constraint);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        EditText pregunta1 = (EditText) findViewById(R.id.pregunta1);
        EditText pregunta2 = (EditText) findViewById(R.id.pregunta2);
        EditText pregunta3 = (EditText) findViewById(R.id.pregunta3);
        EditText pregunta4 = (EditText) findViewById(R.id.pregunta4);
        EditText enunciado = (EditText) findViewById(R.id.enunciado);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context = anadirTarea.this;
        ArrayList<String>categorias = new ArrayList<>();
        Repositorio.cargarCategorias(context);
        categorias = Repositorio.getMisCategorias();


        if(getIntent().hasExtra("codigo") ){

            //Bundle bundle = this.getIntent().getExtras();

            codigoPregunta = getIntent().getExtras().getInt("codigo");

            Pregunta c = miRepo.encuentraPreguntaID(context,codigoPregunta);

            if (c != null) {
                System.out.println("Ee");
            }



            pregunta1.setText(c.getRespuestaCorrecta());
            pregunta2.setText(c.getRespuestaIncorrecta1());
            pregunta3.setText(c.getRespuestaIncorrecta2());
            pregunta4.setText(c.getRespuestaIncorrecta3());
            enunciado.setText(c.getTitulo());




        }





        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //Rellenamos el spinner cuando se crea la pantalla



        //Añadimos un listener para el boton guardar
        final Button btnBotonSimple = (Button)findViewById(R.id.guardaPregunta);
        btnBotonSimple.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(btnBotonSimple.getWindowToken(), 0);
                EditText pregunta1 = (EditText) findViewById(R.id.pregunta1);
                EditText pregunta2 = (EditText) findViewById(R.id.pregunta2);
                EditText pregunta3 = (EditText) findViewById(R.id.pregunta3);
                EditText pregunta4 = (EditText) findViewById(R.id.pregunta4);
                EditText enunciado2 = (EditText) findViewById(R.id.enunciado);
                final String spinner;

                if (getCodigoPregunta() != -1) {
                    Pregunta preguntaActualizar = new Pregunta(enunciado2.getText().toString(), pregunta1.getText().toString(), pregunta2.getText().toString(), pregunta3.getText().toString(), pregunta4.getText().toString(), getCodigoPregunta(), "php");

                    Repositorio.actualizarPregunta(context, preguntaActualizar);
                } else {

                    // Metemos en variables el String de las preguntas

                    context = anadirTarea.this;
                    String respuestaCorrecta = pregunta1.getText().toString();
                    String respuestaIncorrecta1 = pregunta2.getText().toString();
                    String respuestaIncorrecta2 = pregunta3.getText().toString();
                    String respuestaIncorrecta3 = pregunta4.getText().toString();
                    //Necesitamos mirar si todas las preguntas están rellenas
                    //Si una respuesta está sin rellenar, mandamos un mensaje a la snackbar
                    //Si una respuesta está sin rellenar, le cambiamos el color de fondo.
                    if (pregunta1.getText().toString().isEmpty()) {
                        pregunta1.setBackgroundColor(pregunta1.getCurrentHintTextColor());
                        Snackbar.make(view, "Rellena los campos", Snackbar.LENGTH_LONG).
                                setAction("Action", null).show();
                    } else if (pregunta2.getText().toString().isEmpty()) {
                        pregunta2.setBackgroundColor(pregunta2.getCurrentHintTextColor());
                        Snackbar.make(view, "Rellena los campos", Snackbar.LENGTH_LONG).
                                setAction("Action", null).show();
                    } else if (pregunta3.getText().toString().isEmpty()) {
                        pregunta3.setBackgroundColor(pregunta3.getCurrentHintTextColor());
                        Snackbar.make(view, "Rellena los campos", Snackbar.LENGTH_LONG).
                                setAction("Action", null).show();
                    } else if (pregunta4.getText().toString().isEmpty()) {
                        pregunta4.setBackgroundColor(pregunta4.getCurrentHintTextColor());
                        Snackbar.make(view, "Rellena los campos", Snackbar.LENGTH_LONG).
                                setAction("Action", null).show();
                    } else {

                        // Ahora que los campos están comprobados, vamos a ver los permisos
                        context = anadirTarea.this;
                        int WriteExternalStoragePermission = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                        if (WriteExternalStoragePermission != PackageManager.PERMISSION_GRANTED) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                ActivityCompat.requestPermissions(anadirTarea.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, CODE_WRITE_EXTERNAL_STORAGE_PERMISSION);
                            } else {
                                Snackbar.make(constraint, "Permisos denegados, no se guardaran las preguntas.", Snackbar.LENGTH_LONG)
                                        .show();

                            }
                        } else {
                            Snackbar.make(constraint, getResources().getString(R.string.write_permission_granted), Snackbar.LENGTH_LONG)
                                    .show();
                            Repositorio miRepo = new Repositorio();
                            EditText enunciado = findViewById(R.id.enunciado);
                            String enunciadoS = enunciado.getText().toString();
                            Pregunta miPregunta = new Pregunta(enunciadoS, respuestaCorrecta, respuestaIncorrecta1, respuestaIncorrecta2, respuestaIncorrecta3, "php");
                            miRepo.insertaPregunta(context, miPregunta);
                        }
                    }

                }
            }
        });
    }


}

