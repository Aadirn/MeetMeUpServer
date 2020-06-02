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
public interface UsuarioICrud {

    public boolean registrar(UsuarioNoThread usuario);

    public ArrayList<UsuarioNoThread> obtener();

    public boolean actualizar(UsuarioNoThread cliente);

    public boolean eliminar(int id);
    
    public UsuarioNoThread obtenerUnico(int id);
}
