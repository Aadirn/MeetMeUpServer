/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import modelo.Quedada;
import modelo.UsuarioNoThread;

/**
 *
 * @author VSPC-ETERNALSTORM2V5
 */
public class ControllerCreacion {

    private UsuarioNoThread u;
    private Quedada q;

    public UsuarioNoThread creaObjUsuarioRegistrar(String cadenaCreaUsuarioRegistrar) {
        u = new UsuarioNoThread();
        CreaUsuariosI crea = new CreaUsuarios();
        u = crea.registrarToObj(cadenaCreaUsuarioRegistrar);
        return u;
    }

    public UsuarioNoThread creaObjUsuarioActualizar(String cadenaCreaUsuarioActualizar) {
        u = new UsuarioNoThread();
        CreaUsuariosI crea = new CreaUsuarios();
        u = crea.actualizarToObj(cadenaCreaUsuarioActualizar);
        return u;
    }

    public Quedada creaObjQuedada(String cadenaCreaQuedada) {
        q = new Quedada();
        CreaQuedadaI crea = new CreaQuedada();
        q = crea.creaQuedadaDeString(cadenaCreaQuedada);
        return q;
    }

}
