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

public class anadirTarea extends AppCompatActivity {
    final private int CODE_WRITE_EXTERNAL_STORAGE_PERMISSION = 123;
    private Context context;
    private ConstraintLayout constraint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Accedemos al contexto
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_tarea);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //Rellenamos el spinner cuando se crea la pantalla
        Spinner spinner = (Spinner) findViewById(R.id.spinner2);
        String [] letra = {"Examen 1","Examen 2","Examen 3"};
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, letra));
        //Añadimos un listener para el boton guardar
        final Button btnBotonSimple = (Button)findViewById(R.id.guardaPregunta);
        btnBotonSimple.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(btnBotonSimple.getWindowToken(), 0);
                int contador = 0;
                // Metemos en variables el String de las preguntas
                EditText pregunta1 = (EditText) findViewById(R.id.pregunta1);
                EditText pregunta2 = (EditText) findViewById(R.id.pregunta2);
                EditText pregunta3 = (EditText) findViewById(R.id.pregunta3);
                EditText pregunta4 = (EditText) findViewById(R.id.pregunta4);
                //Necesitamos mirar si todas las preguntas están rellenas
                //Si una respuesta está sin rellenar, mandamos un mensaje a la snackbar
                //Si una respuesta está sin rellenar, le cambiamos el color de fondo.
                if ( pregunta1.getText().toString().isEmpty() ){
                    pregunta1.setBackgroundColor(pregunta1.getCurrentHintTextColor());
                    Snackbar.make(view, "Rellena los campos",Snackbar.LENGTH_LONG).
                            setAction("Action",null).show();
                } else if ( pregunta2.getText().toString().isEmpty() ){
                    pregunta2.setBackgroundColor(pregunta2.getCurrentHintTextColor());
                    Snackbar.make(view, "Rellena los campos",Snackbar.LENGTH_LONG).
                            setAction("Action",null).show();
                } else if ( pregunta3.getText().toString().isEmpty() ){
                    pregunta3.setBackgroundColor(pregunta3.getCurrentHintTextColor());
                    Snackbar.make(view, "Rellena los campos",Snackbar.LENGTH_LONG).
                            setAction("Action",null).show();
                } else if ( pregunta4.getText().toString().isEmpty() ){
                    pregunta4.setBackgroundColor(pregunta4.getCurrentHintTextColor());
                    Snackbar.make(view, "Rellena los campos",Snackbar.LENGTH_LONG).
                            setAction("Action",null).show();
                }else{
                    // Ahora que los campos están comprobados, vamos a ver los permisos
                    context = anadirTarea.this;
                    constraint = findViewById(R.id.constraint);
                    int WriteExternalStoragePermission = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    if (WriteExternalStoragePermission != PackageManager.PERMISSION_GRANTED) {
                        // Permiso denegado
                        // A partir de Marshmallow (6.0) se pide aceptar o rechazar el permiso en tiempo de ejecución
                        // En las versiones anteriores no es posible hacerlo
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                            ActivityCompat.requestPermissions(anadirTarea.this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, CODE_WRITE_EXTERNAL_STORAGE_PERMISSION);
                            // Una vez que se pide aceptar o rechazar el permiso se ejecuta el método "onRequestPermissionsResult" para manejar la respuesta
                            // Si el usuario marca "No preguntar más" no se volverá a mostrar este diálogo
                        } else {
                            Snackbar.make(constraint, "Permisos denegados", Snackbar.LENGTH_LONG)
                                    .show();
                        }
                    } else {
                        // Permiso aceptado
                        Snackbar.make(constraint,getResources().getString(R.string.write_permission_granted), Snackbar.LENGTH_LONG)
                                .show();
                    }
                }

            }
        });
    }


}

