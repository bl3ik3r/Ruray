package com.example.beemovil.ruray;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.beemovil.ruray.Data.Adaptadores.AdaptadorTarea;
import com.example.beemovil.ruray.Data.Clases.Tarea;
import com.example.beemovil.ruray.DataBase.ConexionSqlHelper;

import java.util.ArrayList;

public class ListaTareaPendiente extends AppCompatActivity {

    RecyclerView rv_pendientes;
    ArrayList<Tarea> listaTareasPendientes;
    AdaptadorTarea adaptadorTarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tarea_pendiente);


         listaTareasPendientes= new ArrayList<Tarea>();
rv_pendientes=findViewById(R.id.rv_pendientesTareas);

       consultarTareasPendientes();




rv_pendientes.setLayoutManager(new LinearLayoutManager(this));

        adaptadorTarea= new AdaptadorTarea(listaTareasPendientes,this);
        rv_pendientes.setAdapter(adaptadorTarea);




    }

    public void consultarTareasPendientes(){

        ConexionSqlHelper conn= new ConexionSqlHelper(this,"bd_usuarios",null,1);
        System.out.println("fase1: ");
        Tarea tarea=null;

        SQLiteDatabase db=conn.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM tareas",null);

        System.out.println("fase2: "+cursor.getCount());
while(cursor.moveToNext()){

tarea= new Tarea();

tarea.setNombreTarea(cursor.getString(1));
tarea.setFechaTarea(cursor.getString(2));
tarea.setHoraTarea(cursor.getString(3));

tarea.setEstadoTarea(cursor.getString(5));

listaTareasPendientes.add(tarea);
    System.out.println("tamañotareas: "+listaTareasPendientes.size());

}


    }


}
