package com.tomas9080gmail.test_your_might;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.tomas9080gmail.test_your_might.tareas.Pregunta;
import java.util.ArrayList;
public class Repositorio {

    private ArrayList<Pregunta> misPreguntas;

    public void insertaPregunta(Context myContext,Pregunta miPregunta){
        SQLITEHelper miBD = new SQLITEHelper(myContext,"dbPreguntas",null,1);
        SQLiteDatabase db = miBD.getWritableDatabase();

        if(db != null) {
            db.execSQL("INSERT INTO Preguntas (titulo, respuestaCorrecta, respuestaIncorrecta1, respuestaIncorrecta2, respuestaIncorrecta3,categoria)"+
                    "VALUES ('" + miPregunta.getTitulo()+ "', '" + miPregunta.getRespuestaCorrecta() + "', '"+ miPregunta.getRespuestaIncorrecta1()+"', '"+ miPregunta.getRespuestaIncorrecta2()+"', '"+miPregunta.getRespuestaIncorrecta3()+"', '"+miPregunta.getCategoria()+"')");
        }
        db.close();
    }
    public void damePreguntas(Context myContext) {
        misPreguntas = new ArrayList<Pregunta>();

        baseDeDatos usdbh = new baseDeDatos(myContext, "bd", null, 1);
        SQLiteDatabase db = usdbh.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Preguntas", null);
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                String codigo = c.getString(c.getColumnIndex("codigo"));
                String enunciado = c.getString(c.getColumnIndex("enunciado"));
                String categoria = c.getString(c.getColumnIndex("categoria"));
                String respuestaCorrecta = c.getString(c.getColumnIndex("respuestaCorrecta"));
                String respuestaIncorrecta1 = c.getString(c.getColumnIndex("respuestaIncorrecta1"));
                String respuestaIncorrecta2 = c.getString(c.getColumnIndex("respuestaIncorrecta2"));
                String respuestaIncorrecta3 = c.getString(c.getColumnIndex("respuestaIncorrecta3"));
                Pregunta miPregunta = new Pregunta(enunciado,respuestaCorrecta,respuestaIncorrecta1,respuestaIncorrecta2,respuestaIncorrecta3,categoria);
                misPreguntas.add(miPregunta);
            } while (c.moveToNext());

        }
    }



}