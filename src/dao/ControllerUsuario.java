/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import modelo.UsuarioNoThread;

/**
 *
 * @author VSPC-ETERNALSTORM2V5
 */
public class ControllerUsuario {

    private UsuarioNoThread user;

    public ControllerUsuario() {
    }

    public String registrar(String cadenaInfoRegistrar) {
        boolean login;
        user = new UsuarioNoThread();
        System.out.println("registrar" + cadenaInfoRegistrar);
        UsuarioICrud dao = new UsuarioDaoImp();
        CreaUsuariosI crear = new CreaUsuarios();
        user = crear.registrarToObj(cadenaInfoRegistrar);
        if (user == null) {
            return "false";
        }
        login = dao.registrar(user);
        if (login) {//Si es true se ha logeado correctamente
            return "true#" + user.getNickname();
        } else {
            return "false";
        }

    }

    public boolean actualizar(String cadenaInfoActualizar) {
        user = new UsuarioNoThread();
        UsuarioICrud dao = new UsuarioDaoImp();
        CreaUsuariosI crear = new CreaUsuarios();
        user = crear.actualizarToObj(cadenaInfoActualizar);
        return dao.actualizar(user);
    }

    public void eliminar(int idUsuarioDelete) {
        UsuarioICrud dao = new UsuarioDaoImp();
        dao.eliminar(idUsuarioDelete);
    }

    public ArrayList<UsuarioNoThread> obtenerTodos() {

        ArrayList<UsuarioNoThread> usuarios = new ArrayList<>();
        UsuarioICrud dao = new UsuarioDaoImp();
        usuarios = dao.obtener();

        return usuarios;

    }

    public UsuarioNoThread obtenerUnico(int id) {
        UsuarioNoThread u = new UsuarioNoThread();
        UsuarioICrud dao = new UsuarioDaoImp();
        dao.obtenerUnico(id);

        return u;
    }

}
