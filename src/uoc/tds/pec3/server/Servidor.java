/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uoc.tds.pec3.server;
/* EJEMPLO CONEXION RMI PARA TDS/TDP
* Universitat Oberta de Catalunya (UOC) - Primavera de 2012
* Joan Esteve Riasol
*/

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import uoc.tds.pec3.common.Pec3Interface;
import uoc.tds.pec3.server.impl.Pec3Impl;

/**
* Clase principal del servidor
* @author jestever@uoc.edu
*
*/

public class Servidor {

	private final int PORT = 1099;
	private final String JNDI_NAME = "HelloWorld";
	
	public static void main(String args[]) throws IOException{
		new Servidor();
	}
	
	public Servidor(){
		try {
			System.out.println("Iniciando servidor RMI...");
			Registry registry = LocateRegistry.createRegistry(PORT);
			Pec3Interface objetoRemoto = new Pec3Impl();
			registry.rebind(JNDI_NAME, objetoRemoto);
			System.out.println("Servidor iniciado!");
			
		} catch (RemoteException e) {		
			e.printStackTrace();
			
		} catch (Exception e){
			e.printStackTrace();
			
		}
	}
}