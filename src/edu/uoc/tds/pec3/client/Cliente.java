/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uoc.tds.pec3.client;
/* EJEMPLO CONEXION RMI PARA TDS/TDP
* Universitat Oberta de Catalunya (UOC) - Primavera de 2012
* Joan Esteve Riasol
*/

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import edu.uoc.tds.pec3.common.GestorEstocInterface;

/**
* Esta es la clase principal del Cliente
* @author jestever@uoc.edu
*/
public class Cliente {

    private GestorEstocInterface remoto;
    private final String URL = "localhost";
    private final int PORT = 1099;
    private final String JNDI_NAME = "GestorEstoc";

    public static void main(String args[]) throws IOException {
        Cliente cl = new Cliente();
        cl.realizarTest();
    }

    public Cliente() {
        try {
            System.out.println("Conectando al servidor desde cliente...");
            Registry registry = LocateRegistry.getRegistry(URL, PORT);
            remoto = (GestorEstocInterface) registry.lookup(JNDI_NAME);
            System.out.println("Conectado!");
        } catch (NotBoundException e) {
            e.printStackTrace();
            System.exit(0);
        } catch (RemoteException e) {
            e.printStackTrace();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private void realizarTest() {
        System.out.println("Realizando test...");
        try {
            String respuesta = remoto.test();
            System.out.println(respuesta);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}