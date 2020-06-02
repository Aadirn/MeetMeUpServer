/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author VSPC-ETERNALSTORM2V5
 */
public class Quedada {
    
    private String nombreQuedada;
    private int numeroAsistentes;
    private String motivoQuedada;
    //private LOCALIZACION;
    private int numeroUsuariosUnidos;
    private ArrayList<Usuario> usuariosUnidos;
    private Usuario creador;
    private String hora;
    private Calendar creacionQuedada;
    
    public Quedada(String nombreQuedada, int numeroAsistentes, String motivoQuedada, int numeroUsuariosUnidos, ArrayList<Usuario> usuariosUnidos, Usuario creador, String hora, Calendar creacionQuedada) {
        this.nombreQuedada = nombreQuedada;
        this.numeroAsistentes = numeroAsistentes;
        this.motivoQuedada = motivoQuedada;
        this.numeroUsuariosUnidos = numeroUsuariosUnidos;
        this.usuariosUnidos = usuariosUnidos;
        this.creador = creador;
        this.hora = hora;
        this.creacionQuedada = creacionQuedada;
    }
    
    public Calendar getCreacionQuedada() {
        return creacionQuedada;
    }
    
    public void setCreacionQuedada(Calendar creacionQuedada) {
        this.creacionQuedada = creacionQuedada;
    }

    //TEMPORAL v
    public Quedada(String nombreQuedada, int numeroAsistentes, String motivoQuedada, int numeroUsuariosUnidos, String hora) {
        this.nombreQuedada = nombreQuedada;
        this.numeroAsistentes = numeroAsistentes;
        this.motivoQuedada = motivoQuedada;
        this.numeroUsuariosUnidos = numeroUsuariosUnidos;
        this.hora = hora;
    }
    
    @Override
    public String toString() {
        return nombreQuedada + "#" + numeroAsistentes + "#" + motivoQuedada + "#" + numeroUsuariosUnidos + "#" + usuariosUnidos + "#" + creador + "#" + hora + "#" + fechaAStringCorrecta(creacionQuedada);
    }
    
    public Quedada() {
    }
    
    public String getNombreQuedada() {
        return nombreQuedada;
    }
    
    public void setNombreQuedada(String nombreQuedada) {
        this.nombreQuedada = nombreQuedada;
    }
    
    public int getNumeroAsistentes() {
        return numeroAsistentes;
    }
    
    public void setNumeroAsistentes(int numeroAsistentes) {
        this.numeroAsistentes = numeroAsistentes;
    }
    
    public String getMotivoQuedada() {
        return motivoQuedada;
    }
    
    public void setMotivoQuedada(String motivoQuedada) {
        this.motivoQuedada = motivoQuedada;
    }
    
    public int getNumeroUsuariosUnidos() {
        return numeroUsuariosUnidos;
    }
    
    public void setNumeroUsuariosUnidos(int numeroUsuariosUnidos) {
        this.numeroUsuariosUnidos = numeroUsuariosUnidos;
    }
    
    public ArrayList<Usuario> getUsuariosUnidos() {
        return usuariosUnidos;
    }
    
    public void setUsuariosUnidos(ArrayList<Usuario> usuariosUnidos) {
        this.usuariosUnidos = usuariosUnidos;
    }
    
    public Usuario getCreador() {
        return creador;
    }
    
    public void setCreador(Usuario creador) {
        this.creador = creador;
    }
    
    public String getHora() {
        return hora;
    }
    
    public void setHora(String hora) {
        this.hora = hora;
    }
    
    public String fechaAStringCorrecta(Calendar fecha) {
        if (fecha == null) {
            return null;
        }
        String fechaString = fecha.get(Calendar.YEAR) + "-" + (fecha.get(Calendar.MONTH) + 1) + "-" + fecha.get(Calendar.DAY_OF_MONTH);

        //System.out.println(fechaCalendar.get(Calendar.DAY_OF_MONTH)+ " " + fechaCalendar.get(Calendar.MONTH)+" "+fechaCalendar.get(Calendar.YEAR));
        return fechaString;
    }
    
    public Calendar fechaACAlendarCorrecta(String fecha) {
        if (fecha == null) {
            return null;
        }
        Calendar fechaCalendar = Calendar.getInstance();
        String[] fechaDividida = new String[2];
        
        fechaDividida = fecha.split("-");
        
        int year = Integer.parseInt(fechaDividida[0]);
        int month = Integer.parseInt(fechaDividida[1]);
        int day = Integer.parseInt(fechaDividida[2]);
        
        fechaCalendar.set(Calendar.YEAR, year);
        fechaCalendar.set(Calendar.MONTH, month);
        fechaCalendar.set(Calendar.DAY_OF_MONTH, day);
        
        return fechaCalendar;
    }
    
}
