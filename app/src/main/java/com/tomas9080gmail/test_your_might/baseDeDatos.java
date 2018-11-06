package com.tomas9080gmail.test_your_might;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class baseDeDatos extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static String DATABASE_NAME= "preguntas.db";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_CORRECTA = "correcta";
    private static final String COLUMN_INCORRECTA1 = "incorrecta";
    private static final String COLUMN_INCORRECTA2 = "incorrecta";
    private static final String COLUMN_INCORRECTA3 = "incorrecta";

    private static String TABLE_NAME = "pregunta";

   SQLiteDatabase db;

   public baseDeDatos(Context contexto) {
       super (contexto,DATABASE_NAME,null,DATABASE_VERSION);
       db = getWritableDatabase();
   }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+"( "+COLUMN_ID+" INTEGER PRIMARY KEY,"+COLUMN_CORRECTA+" TEXT, "+COLUMN_INCORRECTA1+" TEXT, "+COLUMN_INCORRECTA2+" TEXT, "+COLUMN_INCORRECTA3+" TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Preguntas");
        onCreate(db);


    }
}