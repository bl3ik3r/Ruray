package com.example.beemovil.ruray.Data.Adaptadores;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beemovil.ruray.DataBase.ConexionSqlHelper;
import com.example.beemovil.ruray.R;

import com.example.beemovil.ruray.Data.Clases.Tarea;

import java.util.ArrayList;

public class AdaptadorTarea extends RecyclerView.Adapter<AdaptadorTarea.ViewHolderTarea> {

    ArrayList<Tarea> listaTareaAdapter;
    Context context;

    public AdaptadorTarea(ArrayList<Tarea> listaTareaAdapter,Context context) {
        this.listaTareaAdapter = listaTareaAdapter;
        this.context=context;
    }
    @Override
    @NonNull
    public ViewHolderTarea onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_lista_tarea,null,false);
        return new ViewHolderTarea(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderTarea viewHolderTarea, final int i) {

        viewHolderTarea.nombreTareaRe.setText(listaTareaAdapter.get(i).getNombreTarea());

        System.out.println("nombreyapues: "+listaTareaAdapter.get(i).getNombreTarea());
        viewHolderTarea.fechaTareaRe.setText(listaTareaAdapter.get(i).getFechaTarea());
        viewHolderTarea.horaTareaRe.setText(listaTareaAdapter.get(i).getHoraTarea());
        viewHolderTarea.estadoTareaR.setText(listaTareaAdapter.get(i).getEstadoTarea());


        viewHolderTarea.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage(" ¿Desea eliminar la tarea?")
                        .setTitle("Eliminar")
                        .setCancelable(false)
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {


                                ConexionSqlHelper conn= new ConexionSqlHelper(context,"bd_usuarios",null,1);

                                SQLiteDatabase db=conn.getWritableDatabase();

                                 db.execSQL("DELETE FROM tareas WHERE idD='"+String.valueOf(i+1)+"'");

                                listaTareaAdapter.remove(i);
                                notifyDataSetChanged();

                             dialog.dismiss();
                            }
                        }).setNegativeButton("cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();


                return false;
            }
        });


    }

    @Override
    public int getItemCount() {
        return listaTareaAdapter.size();
    }


    public static class ViewHolderTarea extends RecyclerView.ViewHolder{

        ArrayList<Tarea> listaTareaAdapter;

        TextView nombreTareaRe,fechaTareaRe,horaTareaRe,estadoTareaR;


        public ViewHolderTarea(@NonNull final View itemView) {

            super(itemView);
            nombreTareaRe=itemView.findViewById(R.id.txt_nombreTrareaLista);
            fechaTareaRe=itemView.findViewById(R.id.txt_fechaTareaLista);
            horaTareaRe=itemView.findViewById(R.id.txt_horaTareaLista);
            estadoTareaR=itemView.findViewById(R.id.txt_estadoTareaLista);





        }
    }
}
