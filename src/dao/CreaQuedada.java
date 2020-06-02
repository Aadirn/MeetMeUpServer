/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import modelo.Quedada;
import modelo.Usuario;
import modelo.UsuarioNoThread;

public class CreaQuedada implements CreaQuedadaI {

    private String nombreQuedada;
    private int numeroAsistentes;
    private String motivoQuedada;
    //private LOCALIZACION;
    private int numeroUsuariosUnidos;
    private ArrayList<UsuarioNoThread> usuariosUnidos;
    private UsuarioNoThread creador;
    private String hora;

    @Override
    public Quedada creaQuedadaDeString(String comando) {
        String[] datosUsuario = new String[6];
        datosUsuario = comando.split("#");
        nombreQuedada = datosUsuario[0];
        numeroAsistentes = Integer.parseInt(datosUsuario[1]);
        motivoQuedada = datosUsuario[2];
        numeroUsuariosUnidos = Integer.parseInt(datosUsuario[3]);
        usuariosUnidos = null; //EN VEZ DE PASAR TODOS LOS USUARIOS PASAR SUS ID'S Y BUSCARLOS EN LA BBDD.
        creador = null; //PASAR POR EL COMANDO EL ID DEL USUARIO CREADOR Y ANTES DE AÑADIRLO BUSCARLO EN LA BBDD.
        hora=datosUsuario[6];

        //Usuario user = new Usuario(nickname, nombre, apellido1, apellido2, fechaNacimiento, contrasena);
        //return user;
        return null;
    }

}
