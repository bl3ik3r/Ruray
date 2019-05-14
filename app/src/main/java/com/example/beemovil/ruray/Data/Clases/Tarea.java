package com.example.beemovil.ruray.Data.Clases;

public class Tarea {

    Integer id;
    String nombreTarea;
    String FechaTarea;
    String horaTarea;
    String DetallesTarea;
    String estadoTarea;
    String alarmaTarea;

    public Tarea(Integer id, String nombreTarea, String fechaTarea, String horaTarea, String detallesTarea, String estadoTarea, String alarmaTarea) {
        this.id = id;
        this.nombreTarea = nombreTarea;
        FechaTarea = fechaTarea;
        this.horaTarea = horaTarea;
        DetallesTarea = detallesTarea;
        this.estadoTarea = estadoTarea;
        this.alarmaTarea = alarmaTarea;
    }

    public Tarea(){




    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreTarea() {
        return nombreTarea;
    }

    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    public String getFechaTarea() {
        return FechaTarea;
    }

    public void setFechaTarea(String fechaTarea) {
        FechaTarea = fechaTarea;
    }

    public String getHoraTarea() {
        return horaTarea;
    }

    public void setHoraTarea(String horaTarea) {
        this.horaTarea = horaTarea;
    }

    public String getDetallesTarea() {
        return DetallesTarea;
    }

    public void setDetallesTarea(String detallesTarea) {
        DetallesTarea = detallesTarea;
    }

    public String getEstadoTarea() {
        return estadoTarea;
    }

    public void setEstadoTarea(String estadoTarea) {
        this.estadoTarea = estadoTarea;
    }

    public String getAlarmaTarea() {
        return alarmaTarea;
    }

    public void setAlarmaTarea(String alarmaTarea) {
        this.alarmaTarea = alarmaTarea;
    }
}
