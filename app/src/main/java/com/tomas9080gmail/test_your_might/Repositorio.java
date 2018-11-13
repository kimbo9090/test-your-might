package com.tomas9080gmail.test_your_might;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.tomas9080gmail.test_your_might.tareas.Pregunta;

public class Repositorio {


    public void insertaPregunta(Context myContext,Pregunta miPregunta){
        SQLITEHelper miBD = new SQLITEHelper(myContext,"dbPreguntas",null,1);
        SQLiteDatabase db = miBD.getWritableDatabase();

        if(db != null) {
            db.execSQL("INSERT INTO Usuarios (titulo, respuestaCorrecta,respuestaIncorrecta1,respuestaIncorrecta2,respuestaIncorrecta3,categoria) " +
                    "VALUES (" + miPregunta.getTitulo() + ", '" + miPregunta.getRespuestaCorrecta()+ ", '" + miPregunta.getRespuestaIncorrecta1()+ ", '" + miPregunta.getRespuestaIncorrecta2()
                    + ", '" + miPregunta.getRespuestaIncorrecta3() + ", '" + miPregunta.getCategoria()+ "')");
        }
        db.close();
    }




}
