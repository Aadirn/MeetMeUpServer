/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.awt.Component;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author dgall
 */
public class UsuarioNoThread {

    public UsuarioNoThread() {
    }

    public UsuarioNoThread(Scanner entradaInfo, Scanner entradaRespuesta, PrintWriter salidaInfo, PrintWriter salidaRespuesta, Component cmpt) {
        this.entradaInfo = entradaInfo;
        this.salidaInfo = salidaInfo;
        this.entradaRespuesta = entradaRespuesta;
        this.salidaRespuesta = salidaRespuesta;
        this.cmpt = cmpt;
    }

    public ServerSocket escuchador;
    public Socket conexion;
    private boolean finalizar = false;
    private Scanner entradaInfo;
    private Scanner entradaRespuesta;
    private PrintWriter salidaInfo;
    private PrintWriter salidaRespuesta;
    private Component cmpt;

    private int id;
    private String nickname;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private Calendar fechaCreacion;
    private Calendar fechaNacimiento;
    private String usuariosSeguidos;
    private String contrasena;
    private float valoracion;
    private int vecesValorado;
    private int totalValoraciones;

//    @Override
//    public void run() {
//        do {
//
//            System.out.println("Antes de nextLine en cliente");
//            String respuesta = entradaRespuesta.nextLine();
//            System.out.println("Esta es la respuesta que recibo en el cliente=" + respuesta);
//            //Trocear (primero un nivel "#" y luego a segundo nivel "%", no creo que necesite tercer nivel "&")y hacer Switch
//            //TIPEADO TROCEO= trozo0=accion // trozo1=respuesta // trozo2=info(si hubiera)
//            String[] troceo = respuesta.split("#");
//            String accion = troceo[0];
//            switch (accion) {
//                case "login":
//                    respuestaLogin(troceo[1],troceo[2]);
//                    break;
//                case "crea":
//                    respuestaCrea(troceo[1]);
//                    break;
//
//            }
//        } while (!finalizar);
//    }
    public Component getCmpt() {
        return cmpt;
    }

    public void setCmpt(Component cmpt) {
        this.cmpt = cmpt;
    }

    public Scanner getEntradaInfo() {
        return entradaInfo;
    }

    public void setEntradaInfo(Scanner entradaInfo) {
        this.entradaInfo = entradaInfo;
    }

    public Scanner getEntradaRespuesta() {
        return entradaRespuesta;
    }

    public void setEntradaRespuesta(Scanner entradaRespuesta) {
        this.entradaRespuesta = entradaRespuesta;
    }

    public PrintWriter getSalidaInfo() {
        return salidaInfo;
    }

    public void setSalidaInfo(PrintWriter salidaInfo) {
        this.salidaInfo = salidaInfo;
    }

    public PrintWriter getSalidaRespuesta() {
        return salidaRespuesta;
    }

    public void setSalidaRespuesta(PrintWriter salidaRespuesta) {
        this.salidaRespuesta = salidaRespuesta;
    }

    public ServerSocket getEscuchador() {
        return escuchador;
    }

    public void setEscuchador(ServerSocket escuchador) {
        this.escuchador = escuchador;
    }

    public Socket getConexion() {
        return conexion;
    }

    public void setConexion(Socket conexion) {
        this.conexion = conexion;
    }

    public boolean isFinalizar() {
        return finalizar;
    }

    public void setFinalizar(boolean finalizar) {
        this.finalizar = finalizar;
    }

    //USUARIO COMPLETO v
    public UsuarioNoThread(int id, String nickname, String nombre, String apellido1, String apellido2, Calendar fechaCreacion, Calendar fechaNacimiento, String usuariosSeguidos, String contrasena, float valoracion, int valoracionTotal, int totalValoraciones) {
        this.id = id;
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.fechaCreacion = fechaCreacion;
        this.fechaNacimiento = fechaNacimiento;
        this.usuariosSeguidos = usuariosSeguidos;
        this.contrasena = contrasena;
        this.valoracion = valoracion;
        this.vecesValorado = valoracionTotal;
        this.totalValoraciones = totalValoraciones;
    }

    //(TEMPORAL)Igual no temporal y esto es visualizacion reducida v
    public UsuarioNoThread(int id, String nickname, String nombre, String apellido1, String apellido2) {
        this.id = id;
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
    }

    //USUARIO NUEVO v
    public UsuarioNoThread(String nickname, String nombre, String apellido1, String apellido2, Calendar fechaNacimiento, String contrasena) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.fechaNacimiento = fechaNacimiento;
        this.contrasena = contrasena;
    }

    //USUARIO INFORMACION v
    public UsuarioNoThread(String nickname, String nombre, String apellido1, String apellido2, Calendar fechaNacimiento, String usuariosSeguidos, float valoracion, int vecesValorado, int totalValoraciones) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.fechaNacimiento = fechaNacimiento;
        this.usuariosSeguidos = usuariosSeguidos;
        this.valoracion = valoracion;
        this.vecesValorado = vecesValorado;
        this.totalValoraciones = totalValoraciones;
    }

    public int getVecesValorado() {
        return vecesValorado;
    }

    public void setVecesValorado(int vecesValorado) {
        this.vecesValorado = vecesValorado;
    }

    public int getTotalValoraciones() {
        return totalValoraciones;
    }

    public void setTotalValoraciones(int totalValoraciones) {
        this.totalValoraciones = totalValoraciones;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getContrasena() {
        return contrasena;
    }

    public int getIdUsuario() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public Calendar getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Calendar fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Calendar getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Calendar fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getUsuariosSeguidos() {
        return usuariosSeguidos;
    }

    public void setUsuariosSeguidos(String usuariosSeguidos) {
        this.usuariosSeguidos = usuariosSeguidos;
    }

    public float getValoracion() {
        return valoracion;
    }

    public void setValoracion(float valoracion) {
        this.valoracion = valoracion;

    }

    public String fechaAStringCorrecta(Calendar fecha) {
        if (fecha == null) {
            return null;
        } else {
            String fechaString = fecha.get(Calendar.YEAR) + "-" + (fecha.get(Calendar.MONTH) + 1) + "-" + fecha.get(Calendar.DAY_OF_MONTH);

            //System.out.println(fechaCalendar.get(Calendar.DAY_OF_MONTH)+ " " + fechaCalendar.get(Calendar.MONTH)+" "+fechaCalendar.get(Calendar.YEAR));
            return fechaString;
        }

    }

    public Calendar fechaACAlendarCorrecta(String fecha) {
        if (fecha == null) {
            return null;
        } else {
            Calendar fechaCalendar = Calendar.getInstance();
            String[] fechaDividida = new String[2];

            fechaDividida = fecha.split("-");

            int year = Integer.parseInt(fechaDividida[0]);
            int month = Integer.parseInt(fechaDividida[1])-1;
            int day = Integer.parseInt(fechaDividida[2]);

            fechaCalendar.set(Calendar.YEAR, year);
            fechaCalendar.set(Calendar.MONTH, month);
            fechaCalendar.set(Calendar.DAY_OF_MONTH, day);

            return fechaCalendar;
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UsuarioNoThread other = (UsuarioNoThread) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nickname, other.nickname)) {
            return false;
        }
        return true;
    }

    public String toStringCompleto() {
        return id + "&" + nickname + "&" + nombre + "&" + apellido1 + "&" + apellido2 + "&" + fechaAStringCorrecta(fechaCreacion) + "&" + fechaAStringCorrecta(fechaNacimiento) + "&" + usuariosSeguidos + "&" + contrasena + "&" + valoracion + "&" + vecesValorado + "&" + totalValoraciones;
    }

    public String toStringsSoloId() {
        return id + "";
    }

    @Override
    public String toString() {
        return id + "&" + nickname + "&" + nombre + "&" + apellido1 + "&" + apellido2 + "&" + fechaAStringCorrecta(fechaNacimiento) + "&" + contrasena;
    }

//    private void respuestaCrea(String respuesta) {
//        switch (respuesta) {
//            case "true":
//                System.out.println("Cuenta creada con exito");
//                //Abrir el inicio
//                break;
//            case "false":
//                //Mensaje de advertencia
//                JOptionPane.showMessageDialog(cmpt, "No se ha podido crear la cuenta", "Error al Crear Cuenta", JOptionPane.ERROR_MESSAGE);
//
//                break;
//        }
//    }
//    private void respuestaLogin(String respuesta, String info) {
//        System.out.println(info);
//
//        switch (respuesta) {
//            case "true":
//                System.out.println("Cuenta logeada con exito");
//                //Abrir el inicio
//                UsuarioNoThread u = creaUsuarioLogin(info);
//                break;
//            case "false":
//                //Mensaje de advertencia
//                JOptionPane.showMessageDialog(cmpt, "No se ha podido crear la cuenta", "Error al Crear Cuenta", JOptionPane.ERROR_MESSAGE);
//
//                break;
//        }
//    }
//    private UsuarioNoThread creaUsuarioLogin(String info) {
//        String[] separa = info.split("%");
//        //id + "%" + nickComprobar + "%" + 
//        //passComprobar + "%" + nombre + "%" + apellido1 + "%" + apellido2 + "%" + 
//        //fechaCreacion + "%" + fechaNacimiento + "%" + usuariosSegidos + "%" + valoracion + "%" + 
//        //vecesValorado + "%" + valoracionTotal
//
//    }
}
