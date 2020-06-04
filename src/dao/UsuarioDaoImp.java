/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.UsuarioNoThread;

/*
 */
public class UsuarioDaoImp implements UsuarioICrud {

    private Connection conn;

    @Override
    public boolean registrar(UsuarioNoThread u) {
        try {
            boolean registrar = false;
            initDb();
            try (PreparedStatement stm = conn.prepareStatement("insert into usuarios (nick_usuario,password_usuario,nombre_usuario,apellido1_usuario,apellido2_usuario,fecha_creacion_usuario,fecha_nacimiento_usuario,usuarios_seguidos) values (?,AES_ENCRYPT(?,'admin'),?,?,?,now(),?,'');");) {
                initDb();
                stm.setString(1, u.getNickname());
                String pass = desencriptar(u.getContrasena());
                System.out.println(pass);
                stm.setString(2, pass);
                stm.setString(3, u.getNombre());
                stm.setString(4, u.getApellido1());
                stm.setString(5, u.getApellido2());
                String fechaCorrecta = u.fechaAStringCorrecta(u.getFechaNacimiento());
                stm.setString(6, fechaCorrecta);
                stm.execute();
                registrar = true;
                conn.close();
            } catch (SQLException ex) {
                System.err.println("Fallo en Obtener Datos " + ex.getMessage());
                return registrar;
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(UsuarioDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                return registrar;
            }
            return registrar;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public ArrayList<UsuarioNoThread> obtener() {
        Statement stm = null;
        ResultSet rs = null;
        String fecha;

        String sql = "SELECT id_usuario,nick_usuario,AES_DECRYPT(password_usuario, 'admin'),nombre_usuario, apellido1_usuario, apellido2_usuario, fecha_creacion_usuario,fecha_nacimiento_usuario,usuarios_seguidos, valoraciones, veces_valorado, valoracion_total from usuarios ORDER BY id_usuario ASC";

        ArrayList<UsuarioNoThread> listaUsuario = new ArrayList<>();

        try {
            initDb();
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                UsuarioNoThread u = new UsuarioNoThread();
                u.setId(rs.getInt("id_usuario"));
                u.setNickname(rs.getString("nick_usuario"));
                u.setContrasena(encriptar(rs.getString("AES_DECRYPT(password_usuario, 'admin')")));
                u.setNombre(rs.getString("nombre_usuario"));
                u.setApellido1(rs.getString("apellido1_usuario"));
                u.setApellido2(rs.getString("apellido2_usuario"));

                fecha = rs.getString("fecha_creacion_usuario");
                u.setFechaCreacion(crearCalendarDeString(fecha));

                fecha = rs.getString("fecha_nacimiento_usuario");
                u.setFechaNacimiento(crearCalendarDeString(fecha));
                u.setUsuariosSeguidos(rs.getString("usuarios_seguidos"));
                u.setValoracion(rs.getFloat("valoraciones"));
                u.setVecesValorado(rs.getInt("veces_valorado"));
                u.setTotalValoraciones(rs.getInt("valoracion_total"));
                //                Cliente c = new Cliente();
                //                c.setId(rs.getInt(1));
                //                c.setCedula(rs.getString(2));
                //                c.setNombre(rs.getString(3));
                //                c.setApellido(rs.getString(4));
                //                listaCliente.add(c);
            }
            stm.close();
            rs.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase ClienteDaoImple, método obtener");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UsuarioDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaUsuario;
    }

    @Override
    public boolean actualizar(UsuarioNoThread u) {
        Statement stm = null;
        
        System.out.println("Dentro de UsuarioDaoImp==>"+ u.getBiografia());
        System.out.println("Dentro de UsuarioDaoImp==>"+ u.getIdUsuario());

        boolean actualizar = false;
        //nick_usuario,nombre_usuario,apellido1_usuario,apellido2_usuario,fecha_creacion_usuario,fecha_nacimiento_usuario,usuarios_seguidos
        String fechaUsuario = u.fechaAStringCorrecta(u.getFechaNacimiento());

        String sql = "UPDATE usuarios SET nick_usuario='" + u.getNickname() + "', nombre_usuario='" + u.getNombre() + "', apellido1_usuario='" + u.getApellido1() + "', apellido2_usuario='" + u.getApellido2() + "', fecha_nacimiento_usuario='" + fechaUsuario
                + "', usuarios_seguidos='" + u.getUsuariosSeguidos() + "', num_usuarios_seguidos=" + u.getNumUsuariosSeguidos() + ", valoracion_total=" + u.getTotalValoraciones() + ", veces_valorado=" + u.getVecesValorado() + ", biografia='" + u.getBiografia() + "'" + " WHERE id_usuario=" + u.getIdUsuario() + ";";
        try {
            initDb();
            stm = conn.createStatement();
            stm.execute(sql);
            actualizar = true;
            conn.close();
            stm.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase ClienteDaoImple, método actualizar" + e.getMessage());
        }
        return actualizar;
    }

    @Override
    public boolean eliminar(int id) {

        Statement stm = null;

        boolean eliminar = false;

        String sql = "DELETE FROM usuarios WHERE id_usuario=" + id;
        try {

            stm = conn.createStatement();
            stm.execute(sql);
            eliminar = true;
            conn.close();
            stm.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase ClienteDaoImple, método eliminar");
        }
        return eliminar;
    }

    @Override
    public UsuarioNoThread obtenerUnico(int id) {

        Statement stm = null;
        ResultSet rs = null;
        String fecha;

        String sql = "SELECT id_usuario,nick_usuario,AES_DECRYPT(password_usuario, 'admin'),nombre_usuario, apellido1_usuario, apellido2_usuario, fecha_creacion_usuario,fecha_nacimiento_usuario,usuarios_seguidos, valoraciones, veces_valorado, valoracion_total from usuarios WHERE id_usuario=" + id;
        try {
            initDb();
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);

            UsuarioNoThread u = new UsuarioNoThread();
            u.setId(rs.getInt("id_usuario"));
            u.setNickname(rs.getString("nick_usuario"));
            u.setContrasena(encriptar(rs.getString("AES_DECRYPT(password_usuario, 'admin')")));
            u.setNombre(rs.getString("nombre_usuario"));
            u.setApellido1(rs.getString("apellido1_usuario"));
            u.setApellido2(rs.getString("apellido2_usuario"));

            fecha = rs.getString("fecha_creacion_usuario");
            u.setFechaCreacion(crearCalendarDeString(fecha));

            fecha = rs.getString("fecha_nacimiento_usuario");
            u.setFechaNacimiento(crearCalendarDeString(fecha));
            u.setUsuariosSeguidos(rs.getString("usuarios_seguidos"));
            u.setValoracion(rs.getFloat("valoraciones"));
            u.setVecesValorado(rs.getInt("veces_valorado"));
            u.setTotalValoraciones(rs.getInt("valoracion_total"));

            stm.close();
            rs.close();
            conn.close();
            return u;
        } catch (SQLException | UnsupportedEncodingException ex) {
            Logger.getLogger(UsuarioDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    private void initDb() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost/meetmeup", "root", "1234");
    }

    private static String desencriptar(String s) throws UnsupportedEncodingException {
        byte[] decode = Base64.getDecoder().decode(s.getBytes());
        return new String(decode, "utf-8");
    }

    private static String encriptar(String s) throws UnsupportedEncodingException {
        return Base64.getEncoder().encodeToString(s.getBytes("utf-8"));
    }

    private Calendar crearCalendarDeString(String s) {
        Calendar fechaCreacion = Calendar.getInstance();
        String[] fechaDividida = new String[2];

        fechaDividida = s.split("-");

        int year = Integer.parseInt(fechaDividida[0]);
        int month = Integer.parseInt(fechaDividida[1]);
        int day = Integer.parseInt(fechaDividida[2]);

        fechaCreacion.set(Calendar.YEAR, year);
        fechaCreacion.set(Calendar.MONTH, month);
        fechaCreacion.set(Calendar.DAY_OF_MONTH, day);

        return fechaCreacion;
    }

}
