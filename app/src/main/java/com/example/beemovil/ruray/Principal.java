package com.example.beemovil.ruray;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Principal extends AppCompatActivity {

    Button btnNuevaTarea,btnListaPendientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        btnNuevaTarea = findViewById(R.id.btn_nuevaTarea);
        btnListaPendientes=findViewById(R.id.btn_listaPendientes);





        //----------Ingresar a nueva tarea---------------//


        btnNuevaTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent irTarea= new Intent(Principal.this,RegistrarTarea.class);
                startActivity(irTarea);
            }
        });

        btnListaPendientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent irPendientes= new Intent(Principal.this,ListaTareaPendiente.class);
                startActivity(irPendientes);
            }
        });

    }
}
