/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetmeupserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import static meetmeupserver.ServerFrame.usuariosConectados;

/**
 *
 * @author dgall
 */
public class HiloConexion extends Thread {

    public static ServerSocket escuchador33Info;
    public static ServerSocket escuchador34Respuesta;
    public static Socket conexionInfo;
    public static Socket conexionRespuesta;
    private static HiloServerMain hilo;
    private static boolean finalizar = false;
    private static final HiloConexion instancia = new HiloConexion();
    private String puerto33;
    private String puerto34;
    private int numQuedadas;

    private HiloConexion() {
    }

    public static HiloConexion init() {
        return instancia;
    }

    public static HiloConexion init(String puerto33, String puerto34) {
        instancia.puerto33 = puerto33;
        instancia.puerto34 = puerto34;
        return instancia;
    }

    @Override
    public void run() {
        conectar();
    }

    private void conectar() {
        numQuedadas = 0;
        int puerto33Int;
        int puerto34Int;

        //puerto = txtPuerto.getText();
        if (puerto33.isEmpty() && puerto34.isEmpty()) {
            System.out.println("Puertos vacio");
        } else {
            puerto33Int = Integer.valueOf(puerto33);
            puerto34Int = Integer.valueOf(puerto34);
            try {

                System.out.println("Entro en arrancar");
                escuchador33Info = new ServerSocket(puerto33Int);
                escuchador34Respuesta = new ServerSocket(puerto34Int);

                do {

                    if (!escuchador33Info.isClosed() || !escuchador34Respuesta.isClosed()) {

                        System.out.println("Escuchador no esta cerrado");
                        conexionInfo = escuchador33Info.accept();
                        conexionRespuesta = escuchador34Respuesta.accept();

                        if (conexionInfo.isConnected() && conexionRespuesta.isConnected()) {

                            System.out.println("conexion está conectada");
                            hilo = new HiloServerMain(conexionInfo, conexionRespuesta, new ServerRepeaterInterface() {
                                @Override
                                public void repercutirMensajeQuedada(String quedada) {
                                    numQuedadas++;
                                    PrintWriter s;
                                    System.out.println("Quedada en repercutir mensaje==>" + quedada);
                                    System.out.println(usuariosConectados.size());
                                    for (int i = 0; i < usuariosConectados.size(); i++) {
                                        if (finalizar == true) {
                                            usuariosConectados.get(i).getEntradaInfo().close();
                                            usuariosConectados.get(i).getEntradaRespuesta().close();
                                            usuariosConectados.get(i).getSalidaInfo().close();
                                            usuariosConectados.get(i).getSalidaRespuesta().close();

                                        } else {
                                            System.out.println("Entro en repercutir mensaje else");
                                            System.out.println(quedada);
                                            s = usuariosConectados.get(i).getSalidaRespuesta();
                                            s.print("quedada#" + quedada + "=" + numQuedadas + "\r\n");
                                            s.flush();
                                        }
                                    }
                                }

                                @Override
                                public void repercutirUnionQuedada(String unirse) {
                                    PrintWriter s;
                                    System.out.println("Unirse en repercutir mensaje==>" + unirse);
                                    System.out.println(usuariosConectados.size());
                                    for (int i = 0; i < usuariosConectados.size(); i++) {
                                        if (finalizar == true) {
                                            usuariosConectados.get(i).getEntradaInfo().close();
                                            usuariosConectados.get(i).getEntradaRespuesta().close();
                                            usuariosConectados.get(i).getSalidaInfo().close();
                                            usuariosConectados.get(i).getSalidaRespuesta().close();

                                        } else {
                                            System.out.println("Entro en repercutir mensaje else");
                                            System.out.println(unirse);
                                            s = usuariosConectados.get(i).getSalidaRespuesta();
                                            s.print("unirse#" + unirse + "\r\n");
                                            s.flush();
                                        }
                                    }
                                }
                            });
                            usuariosConectados.add(hilo);
                            hilo.start();

                        } else {
                            System.err.println("No se ha podido conectar");
                        }
                    } else {
                        System.err.println("Escuchador Cerrado");
                    }
                } while (true);

            } catch (IOException ex) {
            }
        }
    }

    void setFinalizar() {
        try {
            //System.out.println("Salgo del bucle");
            //conexion.close();
            escuchador33Info.close();
            escuchador34Respuesta.close();

        } catch (IOException ex) {
            Logger.getLogger(HiloConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
