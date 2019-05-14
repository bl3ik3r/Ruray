package com.example.beemovil.ruray.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexionSqlHelper extends SQLiteOpenHelper {


    final String CREATE_TABLE_TAREA="CREATE TABLE tareas (idD INTEGER PRIMARY KEY AUTOINCREMENT,nombreTareaD TEXT,fechaTareaD TEXT,horaTareaD TEXT,detalleTareaD TEXT,estadoTareaD TEXT,alarmaTareaD TEXT)";

    public ConexionSqlHelper( Context context,String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_TAREA);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

db.execSQL("DROP TABLE IF EXISTS  tareas");
onCreate(db);
    }
}
