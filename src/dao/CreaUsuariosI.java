/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import modelo.UsuarioNoThread;

/**
 *
 * @author VSPC-ETERNALSTORM2V5
 */
public interface CreaUsuariosI {

    public UsuarioNoThread registrarToObj(String comando);

    public UsuarioNoThread actualizarToObj(String comando);

}
