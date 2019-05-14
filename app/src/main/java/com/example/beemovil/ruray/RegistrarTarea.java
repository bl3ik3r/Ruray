package com.example.beemovil.ruray;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.beemovil.ruray.DataBase.ConexionSqlHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RegistrarTarea extends AppCompatActivity {

Button btnGuardarTarea;
Switch switchAlarma;
EditText editNombreTarea,editFechaTarea,editHoraTarea,editDetalleTarea;

// -------Fecha con calendar-----//


int dia,mes,year,hora,minutos;

int diaSelec,mesSelec,yearSelec,horaSelec,minutosSelec;
String diaDeLaSemana;


    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;

ImageButton btnFechaTarea,btnHoraTarea;


    // -------Fecha con calendar-----//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_tarea);

        //-------- llamar a los items del layout----------
        btnFechaTarea=findViewById(R.id.btn_fechaTarea);
        btnHoraTarea=findViewById(R.id.btn_horaTarea);
        btnGuardarTarea=findViewById(R.id.btn_guardarTarea);
        switchAlarma=findViewById(R.id.switch_alarmaTarea);
editNombreTarea=findViewById(R.id.edit_nombreTarea);
editFechaTarea=findViewById(R.id.edit_fechaTarea);
editHoraTarea=findViewById(R.id.edit_horaTarea);
editDetalleTarea=findViewById(R.id.edit_detalleTarea);

        editFechaTarea.setFocusable(false);
        editHoraTarea.setFocusable(false);


        //-------- llamar a los items del layout----------



        btnGuardarTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insertarTarea();

            }
        });

        btnFechaTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionarFechaTarea();
            }
        });

        btnHoraTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                horaFinalTarea();
            }
        });


    }






    public void insertarTarea(){

if(switchAlarma.isChecked()){

selecionarAlarma();

}


        ConexionSqlHelper conn= new ConexionSqlHelper(this,"bd_usuarios",null,1);


        SQLiteDatabase db= conn.getWritableDatabase();

        String insert="INSERT INTO TAREAS(nombreTareaD,fechaTareaD,horaTareaD,detalleTareaD,estadoTareaD,alarmaTareaD) values('"+editNombreTarea.getText().toString()+"','"+editFechaTarea.getText().toString()+"','"+editHoraTarea.getText().toString()+"','"+editDetalleTarea.getText().toString()+"','pendiente','"+switchAlarma.isChecked()+"')";


        System.out.println("valoralarma: "+switchAlarma.isChecked());
        Toast.makeText(this,"ingreso correctamente",Toast.LENGTH_SHORT).show();

        db.execSQL(insert);

        db.close();


    }






    @SuppressLint("NewApi")
    public void selecionarAlarma(){


        alarmMgr = (AlarmManager)getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getApplicationContext(), AlarmaActivity.class);
        alarmIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);


        Calendar calendar = Calendar.getInstance();
        // System.out.println("horaCalendar:"+calendar.get(Calendar.HOUR_OF_DAY)+"-"+calendar.get(Calendar.MINUTE));
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.YEAR, yearSelec);
        calendar.set(Calendar.MONTH, mesSelec);
        calendar.set(Calendar.DAY_OF_MONTH, diaSelec);
        calendar.set(Calendar.HOUR_OF_DAY,horaSelec);
        calendar.set(Calendar.MINUTE,minutosSelec);
        calendar.set(Calendar.SECOND, 0);

        System.out.println("horaCalendarfull:"+calendar.get(Calendar.MINUTE)+"-"+calendar.get(Calendar.DAY_OF_MONTH));

        alarmMgr.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmIntent);


    }
    public void seleccionarFechaTarea(){

        Calendar c= Calendar.getInstance();

        dia=c.get(Calendar.DAY_OF_MONTH);
        mes =c.get(Calendar.MONTH);
        year=c.get(Calendar.YEAR);

        DatePickerDialog fecha= new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                diaSelec=dayOfMonth;
                mesSelec=month;
                yearSelec=year;

                final int mesActual = month + 1;
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                String diaFormateado = (dayOfMonth < 10)? "0" + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                String mesFormateado = (mesActual < 10)? "0" + String.valueOf(mesActual):String.valueOf(mesActual);





                SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
                String fecha = String.valueOf(year)+"-"+mesFormateado+"-"+diaFormateado;
                Date fecha1 = null;
                try {
                    fecha1 = formatoDelTexto.parse(fecha);

                    SimpleDateFormat sdf = new SimpleDateFormat("EEEE d, MMMM , yyyy");

                    diaDeLaSemana = sdf.format(fecha1);

                } catch (ParseException ex) {

                    ex.printStackTrace();

                }



                editFechaTarea.setText(diaDeLaSemana);


            }
        },year,mes,dia);

        fecha.show();


    }
    public void horaFinalTarea(){
        Calendar c= Calendar.getInstance();
        hora=c.get(Calendar.HOUR_OF_DAY);
        minutos=c.get(Calendar.MINUTE);

        TimePickerDialog time = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                horaSelec=hourOfDay;
                minutosSelec=minute;

//Formateo el hora obtenido: antepone el 0 si son menores de 10
                String horaFormateada =  (hourOfDay < 10)? String.valueOf("0" + hourOfDay) : String.valueOf(hourOfDay);
                //Formateo el minuto obtenido: antepone el 0 si son menores de 10
                String minutoFormateado = (minute < 10)? String.valueOf("0" + minute):String.valueOf(minute);
                //Obtengo el valor a.m. o p.m., dependiendo de la selección del usuario



                String AM_PM ;
                if(hourOfDay < 12) {
                    AM_PM = "AM";
                } else {
                    AM_PM = "PM";
                }


                editHoraTarea.setText(horaFormateada + ":" + minutoFormateado + " " + AM_PM);

            }
        },hora,minutos,false);

        time.show();

    }

}
