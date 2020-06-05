/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetmeupserver;

import dao.ControllerUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dgall
 */
public class HiloServerMain extends Thread {

    private static final HiloServerMain instancia = new HiloServerMain();
    private ServerRepeaterInterface repeater;
    private boolean salir = true;
    private String comandoInfo;
    private Socket conexion33Info;
    private Socket conexion34Respuesta;
    private Scanner entradaInfo;
    private Scanner entradaRespuesta;
    private PrintWriter salidaInfo;
    private PrintWriter salidaRespuesta;
    private Connection conn;

    private HiloServerMain() {
    }

    public static HiloServerMain init() {
        return instancia;
    }

    public static HiloServerMain init(Socket conexion33Info, Socket conexion34Respuesta, ServerRepeaterInterface repeater) {
        instancia.conexion33Info = conexion33Info;
        instancia.conexion34Respuesta = conexion34Respuesta;
        instancia.repeater = repeater;
        return instancia;
    }

    @Override
    public void run() {
        do {
            try {
                entradaInfo = new Scanner(conexion33Info.getInputStream());
                entradaRespuesta = new Scanner(conexion34Respuesta.getInputStream());

                salidaInfo = new PrintWriter(conexion33Info.getOutputStream());
                salidaRespuesta = new PrintWriter(conexion34Respuesta.getOutputStream());

                System.out.println("Antes de entrar al metodo captar mensaje");
                salidaInfo.flush();
                salidaRespuesta.flush();

                captarMensaje();
            } catch (IOException ex) {
                System.err.println("Fallo al obtener puntos de E/S: " + ex.getMessage());
            }

        } while (salir);
    }

    public boolean isSalir() {
        return salir;
    }

    public void setSalir(boolean salir) {
        this.salir = salir;
    }

    public Socket getConexion33Info() {
        return conexion33Info;
    }

    public void setConexion33Info(Socket conexion33Info) {
        this.conexion33Info = conexion33Info;
    }

    public Socket getConexion34Respuesta() {
        return conexion34Respuesta;
    }

    public void setConexion34Respuesta(Socket conexion34Respuesta) {
        this.conexion34Respuesta = conexion34Respuesta;
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

    private void captarMensaje() { //CAMBIAR TOdo LO MÁS SEGURO
        String[] comandoTroceado;
        String orden;
        System.out.println("antes de nexline");
        //if (conexion.isConnected()) {

        comandoInfo = entradaInfo.nextLine();
        System.out.println(comandoInfo);

        //Tendria que llegarme algo tipo = Accion#Nickname#mensaje (si hay mensaje)
        //Siendo accion: conectar, salir o el mensaje
        comandoTroceado = comandoInfo.split("%");
        orden = comandoTroceado[0];
        System.out.println("Antes del switch\n");
        switch (orden) {
            case "login":
                System.out.println("Dentro de login\n");

                login(comandoTroceado[1], comandoTroceado[2]);

                break;
            case "crear":
                System.out.println("Dentro crear");
                crear(comandoTroceado[1]);
                break;
            case "actualiza":
                actualiza(comandoTroceado[1]);
                break;
            case "quedada":
                quedada(comandoTroceado[1]);
                break;
            //AQUI SEGURAMENTE AÑADIR MÁS

            default:
                break;
        }
        //} else {
        //System.out.println("Conexion no conectada, ekisde");
        //}
    }

    private void login(String nick, String pass) {
        System.out.println("Dentro de metodo login\n");
        System.out.println(nick + "//" + pass + "\n");
        String nickComprobar;
        String passComprobar;
        String nombre;
        String apellido1;
        String apellido2;
        String id;
        String fechaCreacion;
        String fechaNacimiento;
        String usuariosSegidos;
        String numUsuariosSeguidos;
        String valoracionTotal;
        String vecesValorado;
        String valoracion;
        String biografia;

        String sql = "SELECT id_usuario,nick_usuario,AES_DECRYPT(password_usuario, 'admin'),nombre_usuario, apellido1_usuario, apellido2_usuario, fecha_creacion_usuario,fecha_nacimiento_usuario,usuarios_seguidos,num_usuarios_seguidos, valoracion_total, veces_valorado,biografia from usuarios WHERE nick_usuario='" + nick + "'";
        try {

            initDb();
            try (PreparedStatement usuario = conn.prepareStatement(sql);) {

                ResultSet rs = usuario.executeQuery();
                //while (rs.next()) {
                //System.out.println(rs.getString("nick_usuario"));
                if (rs.next()) {
                    System.out.println("Login Correcto\n");

                    id = rs.getString("id_usuario");
                    nickComprobar = rs.getString("nick_usuario");
                    passComprobar = rs.getString("AES_DECRYPT(password_usuario, 'admin')");
                    nombre = rs.getString("nombre_usuario");
                    apellido1 = rs.getString("apellido1_usuario");
                    apellido2 = rs.getString("apellido2_usuario");
                    fechaCreacion = rs.getString("fecha_creacion_usuario");
                    fechaNacimiento = rs.getString("fecha_nacimiento_usuario");
                    usuariosSegidos = rs.getString("usuarios_seguidos");
                    numUsuariosSeguidos = rs.getString("num_usuarios_seguidos");
                    valoracionTotal = compruebaSiNull(rs.getString("valoracion_total"));
                    vecesValorado = compruebaSiNull(rs.getString("veces_valorado"));
                    valoracion = calcularValoracion(valoracionTotal, vecesValorado);
                    biografia = rs.getString("biografia");

                    if (!pass.equals(passComprobar)) {
                        System.out.println("Login fallido no coincide la contraseña\n");
                        conn.close();
                        salidaRespuesta.print("login#false#pass\r\n");
                        salidaRespuesta.flush();
                    }

                    //PONER EN LA SALIDA TODO LOS DATOS DEL COMPA
                    String respuesta = id + "%" + nickComprobar + "%" + passComprobar + "%" + nombre + "%" + apellido1 + "%" + apellido2 + "%" + fechaCreacion + "%" + fechaNacimiento + "%" + usuariosSegidos + "%" + numUsuariosSeguidos + "%" + valoracion + "%" + vecesValorado + "%" + valoracionTotal + "%" + biografia;
                    salidaRespuesta.print("login#true#" + respuesta + "\r\n");
                    salidaRespuesta.flush();
                    conn.close();

                } else {
                    System.out.println("Login Fallido no existe usuario\n");
                    //Enviar Confirmacion de login
                    conn.close();
                    salidaRespuesta.print("login#false#user\r\n");
                    salidaRespuesta.flush();
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(HiloServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initDb() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost/meetmeup", "root", "1234");
    }

    private void crear(String usuario) { //Devolver respuesta de creacion correcta
        System.out.println(usuario);
        ControllerUsuario cntr = new ControllerUsuario();
        String registrar = cntr.registrar(usuario);
        String[] division = registrar.split("#");
        String booleano = division[0];
        if (booleano.equals("true")) {
            String id = obtenerIdBd(division[1]);
            salidaRespuesta.print("crea#true#" + id + "\r\n");
            salidaRespuesta.flush();
        } else {
            salidaRespuesta.print("crea#false#null\r\n");
            salidaRespuesta.flush();
        }

    }

    private String calcularValoracion(String valoracionTotal, String vecesValorado) {
        System.out.println("Calcula Valoracion-->" + valoracionTotal);
        if ("0".equals(valoracionTotal) && "0".equals(vecesValorado)) {
            System.out.println("No hay valoraciones\n");
            return "0";
        }
        float valoracionTotalFloat = Float.parseFloat(valoracionTotal);
        float vecesValoradoFloat = Float.parseFloat(vecesValorado);

        float valoracion = valoracionTotalFloat / vecesValoradoFloat;
        String valoracionString = valoracion + "";
        System.out.println(valoracionString + "\n");

        return valoracionString;

    }

    private String compruebaSiNull(String comprobarNull) {
        if (comprobarNull == null) {
            System.out.println("Esnull\n");
            return "0";
        }
        return comprobarNull;
    }

    private String obtenerIdBd(String nickname) {
        String id = null;
        try {
            initDb();
            String sql = "select id_usuario from usuarios where nick_usuario='" + nickname + "'";
            PreparedStatement usuario = conn.prepareStatement(sql);
            ResultSet rs = usuario.executeQuery();
            while (rs.next()) {
                id = rs.getString("id_usuario");
            }

        } catch (SQLException ex) {
            Logger.getLogger(HiloServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    private void actualiza(String usuario) {
        System.out.println("Dentro de actualiza==>" + usuario);
        ControllerUsuario cntr = new ControllerUsuario();
        cntr.actualizar(usuario);
    }

    private void quedada(String quedada) {
        if (repeater != null) {
            repeater.repercutirMensaje(quedada);

        }
    }

}
