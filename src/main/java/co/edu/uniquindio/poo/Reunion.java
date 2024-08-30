package co.edu.uniquindio.poo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.LinkedList;

public class Reunion {
    String descripcion;
    LocalDate fecha;
    LocalTime hora;
    public Collection<Contacto> contactos;
    public Reunion(String descripcion, LocalDate fecha, LocalTime hora ){
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.hora = hora;
        contactos = new LinkedList<>();
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public LocalTime getHora() {
        return hora;
    }
    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
    
    
}
