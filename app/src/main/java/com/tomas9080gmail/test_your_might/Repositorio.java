package com.tomas9080gmail.test_your_might;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.tomas9080gmail.test_your_might.tareas.Pregunta;
import java.util.ArrayList;

import static com.tomas9080gmail.test_your_might.Constantes.nombreBD;
import static com.tomas9080gmail.test_your_might.Constantes.tablaNombre;

public class Repositorio {

    private static ArrayList<Pregunta> misPreguntas;
    private static ArrayList<String> misCategorias;


    public void insertaPregunta(Context myContext,Pregunta miPregunta){
        SQLITEHelper miBD = new SQLITEHelper(myContext,nombreBD,null,1);
        SQLiteDatabase db = miBD.getWritableDatabase();

        if(db != null) {
            db.execSQL("INSERT INTO Preguntas (titulo, respuestaCorrecta, respuestaIncorrecta1, respuestaIncorrecta2, respuestaIncorrecta3,categoria)"+
                    "VALUES ('" + miPregunta.getTitulo()+ "', '" + miPregunta.getRespuestaCorrecta() + "', '"+ miPregunta.getRespuestaIncorrecta1()+"', '"+ miPregunta.getRespuestaIncorrecta2()+"', '"+miPregunta.getRespuestaIncorrecta3()+"', '"+miPregunta.getCategoria()+"')");
        }
        db.close();
    }
    public static void damePreguntas(Context myContext) {
        misPreguntas = new ArrayList<Pregunta>();

        SQLITEHelper usdbh = new SQLITEHelper(myContext,nombreBD, null, 1);
        SQLiteDatabase db = usdbh.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Preguntas", null);
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                int codigo = c.getInt(c.getColumnIndex("codigo"));
                String enunciado = c.getString(c.getColumnIndex("titulo"));
                String categoria = c.getString(c.getColumnIndex("categoria"));
                String respuestaCorrecta = c.getString(c.getColumnIndex("respuestaCorrecta"));
                String respuestaIncorrecta1 = c.getString(c.getColumnIndex("respuestaIncorrecta1"));
                String respuestaIncorrecta2 = c.getString(c.getColumnIndex("respuestaIncorrecta2"));
                String respuestaIncorrecta3 = c.getString(c.getColumnIndex("respuestaIncorrecta3"));
                Pregunta miPregunta = new Pregunta(enunciado,respuestaCorrecta,respuestaIncorrecta1,respuestaIncorrecta2,respuestaIncorrecta3,codigo,categoria);
                misPreguntas.add(miPregunta);
            } while (c.moveToNext());

        }

    }
    public static void cargarCategorias(Context myContext){
        misCategorias = new ArrayList<>();
        SQLITEHelper usdbh = new SQLITEHelper(myContext, nombreBD, null, 1);
        SQLiteDatabase db = usdbh.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT DISTINCT categoria from Preguntas", null);
        if (c.moveToFirst()) {
            do {
                String categoria = c.getString(c.getColumnIndex("categoria"));
                misCategorias.add(categoria);
            } while (c.moveToNext());
        }


    }

    public static Pregunta encuentraPreguntaID(Context myContext,int codigo){
        Pregunta miPregunta = null;
        SQLITEHelper usdbh = new SQLITEHelper(myContext,nombreBD, null, 1);
        SQLiteDatabase db = usdbh.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Preguntas WHERE codigo='"+codigo+"'", null);
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            String enunciado = c.getString(c.getColumnIndex("titulo"));
            String categoria = c.getString(c.getColumnIndex("categoria"));
            String respuestaCorrecta = c.getString(c.getColumnIndex("respuestaCorrecta"));
            String respuestaIncorrecta1 = c.getString(c.getColumnIndex("respuestaIncorrecta1"));
            String respuestaIncorrecta2 = c.getString(c.getColumnIndex("respuestaIncorrecta2"));
            String respuestaIncorrecta3 = c.getString(c.getColumnIndex("respuestaIncorrecta3"));
            miPregunta = new Pregunta(enunciado, respuestaCorrecta, respuestaIncorrecta1, respuestaIncorrecta2, respuestaIncorrecta3, codigo,categoria);

            return miPregunta;
        }

        return miPregunta;
        }


        public static boolean actualizarPregunta(Context myContext, Pregunta p) {
            boolean val = true;
            SQLITEHelper usdbh = new SQLITEHelper(myContext,nombreBD, null, 1);
            SQLiteDatabase db = usdbh.getWritableDatabase();
            if ( db!= null ){
                ContentValues vals = new ContentValues();
                vals.put("titulo",p.getTitulo());
                vals.put("categoria",p.getCategoria());
                vals.put("respuestaCorrecta",p.getRespuestaCorrecta());
                vals.put("respuestaIncorrecta1",p.getRespuestaIncorrecta1());
                vals.put("respuestaIncorrecta2",p.getRespuestaIncorrecta2());
                vals.put("respuestaIncorrecta3",p.getRespuestaIncorrecta3());

                String [] args = new String [] {Integer.toString(p.getCodigo())};
                db.update(tablaNombre,vals, "codigo=?",args);
                db.close();

            }else {val = false;}
            return val;
        }




    public static ArrayList<String> getMisCategorias() {
        return misCategorias;
    }

    public static ArrayList<Pregunta> getMisPreguntas(){
        return misPreguntas;
    }



}