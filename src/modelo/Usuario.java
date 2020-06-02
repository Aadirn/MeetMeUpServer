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
public class Usuario {

    private static final Usuario instancia = new Usuario();

    private Usuario() {
    }

    public static Usuario init() {
        return instancia;
    }

    public static Usuario init(Scanner entradaInfo, Scanner entradaRespuesta, PrintWriter salidaInfo, PrintWriter salidaRespuesta, Component cmpt) {
        instancia.entradaInfo = entradaInfo;
        instancia.salidaInfo = salidaInfo;
        instancia.entradaRespuesta = entradaRespuesta;
        instancia.salidaRespuesta = salidaRespuesta;
        instancia.cmpt = cmpt;
        return instancia;
    }

    public static ServerSocket escuchador;
    public static Socket conexion;
    private static boolean finalizar = false;
    private Scanner entradaInfo;
    private Scanner entradaRespuesta;
    private PrintWriter salidaInfo;
    private PrintWriter salidaRespuesta;
    private Component cmpt;

    private static int id;
    private static String nickname;
    private static String nombre;
    private static String apellido1;
    private static String apellido2;
    private static Calendar fechaCreacion;
    private static Calendar fechaNacimiento;
    private static String usuariosSeguidos;
    private static String contrasena;
    private static float valoracion;
    private static int vecesValorado;
    private static int totalValoraciones;

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

    public static ServerSocket getEscuchador() {
        return escuchador;
    }

    public static void setEscuchador(ServerSocket escuchador) {
        Usuario.escuchador = escuchador;
    }

    public static Socket getConexion() {
        return conexion;
    }

    public static void setConexion(Socket conexion) {
        Usuario.conexion = conexion;
    }

    public static boolean isFinalizar() {
        return finalizar;
    }

    public static void setFinalizar(boolean finalizar) {
        Usuario.finalizar = finalizar;
    }

    //USUARIO COMPLETO v
    public static Usuario init(int id, String nickname, String nombre, String apellido1, String apellido2, Calendar fechaCreacion, Calendar fechaNacimiento, String usuariosSeguidos, String contrasena, float valoracion, int valoracionTotal, int totalValoraciones) {
        instancia.id = id;
        instancia.nickname = nickname;
        instancia.nombre = nombre;
        instancia.apellido1 = apellido1;
        instancia.apellido2 = apellido2;
        instancia.fechaCreacion = fechaCreacion;
        instancia.fechaNacimiento = fechaNacimiento;
        instancia.usuariosSeguidos = usuariosSeguidos;
        instancia.contrasena = contrasena;
        instancia.valoracion = valoracion;
        instancia.vecesValorado = valoracionTotal;
        instancia.totalValoraciones = totalValoraciones;
        return instancia;
    }

    //(TEMPORAL)Igual no temporal y esto es visualizacion reducida v
    public static Usuario init(int id, String nickname, String nombre, String apellido1, String apellido2) {
        instancia.id = id;
        instancia.nickname = nickname;
        instancia.nombre = nombre;
        instancia.apellido1 = apellido1;
        instancia.apellido2 = apellido2;
        return instancia;
    }

    //USUARIO NUEVO v
    public static Usuario init(String nickname, String nombre, String apellido1, String apellido2, Calendar fechaNacimiento, String contrasena) {
        instancia.nickname = nickname;
        instancia.nombre = nombre;
        instancia.apellido1 = apellido1;
        instancia.apellido2 = apellido2;
        instancia.fechaNacimiento = fechaNacimiento;
        instancia.contrasena = contrasena;
        return instancia;
    }

    //USUARIO INFORMACION v
    public static Usuario init(String nickname, String nombre, String apellido1, String apellido2, Calendar fechaNacimiento, String usuariosSeguidos, float valoracion, int vecesValorado, int totalValoraciones) {
        instancia.nickname = nickname;
        instancia.nombre = nombre;
        instancia.apellido1 = apellido1;
        instancia.apellido2 = apellido2;
        instancia.fechaNacimiento = fechaNacimiento;
        instancia.usuariosSeguidos = usuariosSeguidos;
        instancia.valoracion = valoracion;
        instancia.vecesValorado = vecesValorado;
        instancia.totalValoraciones = totalValoraciones;
        return instancia;
    }

    public int getVecesValorado() {
        return vecesValorado;
    }

    public void setVecesValorado(int vecesValorado) {
        instancia.vecesValorado = vecesValorado;
    }

    public int getTotalValoraciones() {
        return totalValoraciones;
    }

    public void setTotalValoraciones(int totalValoraciones) {
        instancia.totalValoraciones = totalValoraciones;
    }

    public void setContrasena(String contrasena) {
        instancia.contrasena = contrasena;
    }

    public String getContrasena() {
        return contrasena;
    }

    public int getIdUsuario() {
        return id;
    }

    public void setId(int id) {
        instancia.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        instancia.nickname = nickname;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        instancia.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        instancia.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        instancia.apellido2 = apellido2;
    }

    public Calendar getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Calendar fechaCreacion) {
        instancia.fechaCreacion = fechaCreacion;
    }

    public Calendar getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Calendar fechaNacimiento) {
        instancia.fechaNacimiento = fechaNacimiento;
    }

    public String getUsuariosSeguidos() {
        return usuariosSeguidos;
    }

    public void setUsuariosSeguidos(String usuariosSeguidos) {
        instancia.usuariosSeguidos = usuariosSeguidos;
    }

    public float getValoracion() {
        return valoracion;
    }

    public void setValoracion(float valoracion) {
        instancia.valoracion = valoracion;

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
            int month = Integer.parseInt(fechaDividida[1]);
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
        if (instancia == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (instancia.id != other.id) {
            return false;
        }
        if (!Objects.equals(instancia.nickname, other.nickname)) {
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
//                Usuario u = creaUsuarioLogin(info);
//                break;
//            case "false":
//                //Mensaje de advertencia
//                JOptionPane.showMessageDialog(cmpt, "No se ha podido crear la cuenta", "Error al Crear Cuenta", JOptionPane.ERROR_MESSAGE);
//
//                break;
//        }
//    }

//    private Usuario creaUsuarioLogin(String info) {
//        String[] separa = info.split("%");
//        //id + "%" + nickComprobar + "%" + 
//        //passComprobar + "%" + nombre + "%" + apellido1 + "%" + apellido2 + "%" + 
//        //fechaCreacion + "%" + fechaNacimiento + "%" + usuariosSegidos + "%" + valoracion + "%" + 
//        //vecesValorado + "%" + valoracionTotal
//
//    }

}
