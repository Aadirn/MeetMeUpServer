/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.UsuarioNoThread;

/**
 *
 * @author VSPC-ETERNALSTORM2V5
 */
public class CreaUsuarios implements CreaUsuariosI {

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
    private UsuarioNoThread u = new UsuarioNoThread();

    @Override
    public UsuarioNoThread registrarToObj(String comando) {
        //nick_usuario,password_usuario,nombre_usuario,apellido1_usuario,apellido2_usuario,fecha_nacimiento_usuario
        String[] datosUsuario;
        datosUsuario = comando.split("&");
        //nickname, nombre, apellido1, apellido2, fechaNacimiento, encriptar(contrasena)
        nickname = datosUsuario[1];
        contrasena = datosUsuario[6];
        System.out.println(contrasena);
        nombre = datosUsuario[2];
        apellido1 = datosUsuario[3];
        apellido2 = datosUsuario[4];
        fechaNacimiento = u.fechaACAlendarCorrecta(datosUsuario[5]);
        if(comprobarUsuarioRepetido(nickname)){
            return null;
        }

        UsuarioNoThread user = new UsuarioNoThread(nickname, nombre, apellido1, apellido2, fechaNacimiento, contrasena);
        return user;

    }

    @Override
    public UsuarioNoThread actualizarToObj(String comando) {
        //nick_usuario+, nombre_usuario+ "', apellido1_usuario "', apellido2_usuario='"', fecha_nacimiento_usuario=', valoraciones='" + "', veces_valorado='" ++ "', valoracion_total='
        String[] datosUsuario = new String[7];
        datosUsuario = comando.split("#");
        nickname = datosUsuario[0];
        nombre = datosUsuario[1];
        apellido1 = datosUsuario[2];
        apellido2 = datosUsuario[3];
        fechaNacimiento = u.fechaACAlendarCorrecta(datosUsuario[4]);
        valoracion = Float.parseFloat(datosUsuario[5]);
        vecesValorado = Integer.parseInt(datosUsuario[6]);
        totalValoraciones = Integer.parseInt(datosUsuario[7]);

        UsuarioNoThread user = new UsuarioNoThread(nickname, nombre, apellido1, apellido2, fechaNacimiento, usuariosSeguidos, valoracion, vecesValorado, totalValoraciones);
        return user;

    }

    private boolean comprobarUsuarioRepetido(String nicknameLocal) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/meetmeup", "root", "1234");
            String sql = "select nick_usuario from usuarios";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                String nickComprobar=rs.getString("nick_usuario");
                
                if(nicknameLocal.equals(nickComprobar)){
                    System.out.println("Nicks iguales");
                    return true;
                }
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CreaUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
