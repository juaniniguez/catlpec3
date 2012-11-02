/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uoc.tds.pec3.cliente;
/* EJEMPLO CONEXION RMI PARA TDS/TDP
* Universitat Oberta de Catalunya (UOC) - Primavera de 2012
* Joan Esteve Riasol
*/

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import uoc.tds.pec3.common.Pec3Interface;

/**
* Esta es la clase principal del Cliente
* @author jestever@uoc.edu
*/
public class Cliente {

	private Pec3Interface remoto;
	private final String URL = "localhost";
	private final int PORT = 1099;
	private final String JNDI_NAME = "HelloWorld";
	
	public static void main(String args[]) throws IOException{
		Cliente cl = new Cliente();
		cl.realizarTest();
	}
	public Cliente(){
		try{
			System.out.println("Conectando al servidor desde cliente...");
			Registry registry = LocateRegistry.getRegistry(URL,PORT);
			remoto=(Pec3Interface)registry.lookup(JNDI_NAME);
			System.out.println("Conectado!");
			}catch (NotBoundException e) {
			e.printStackTrace();
			System.exit(0);
			}catch (RemoteException e) {
			e.printStackTrace();
			System.exit(0);
			}catch (Exception e){
			e.printStackTrace();
			System.exit(0);
			}
	}
	
	private void realizarTest(){
		System.out.println("Realizando test...");
		try {
			String respuesta = remoto.test();
			System.out.println(respuesta);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}