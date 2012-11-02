/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uoc.tds.pec3.server.impl;
/* EJEMPLO CONEXION RMI PARA TDS/TDP
* Universitat Oberta de Catalunya (UOC) - Primavera de 2012
* Joan Esteve Riasol
*/

import java.io.Serializable;
import java.rmi.RemoteException;
import uoc.tds.pec3.common.Pec3Interface;

/**
* Clase implementacion de la interficie RMI. Capa de negocio.
* Esta clase solo es de la parte servidor.
* @author jestever@uoc.edu
*/

public class Pec3Impl extends java.rmi.server.UnicastRemoteObject implements Pec3Interface,Serializable{

	private static final long serialVersionUID = 1L;
	
	public Pec3Impl() throws RemoteException {
		super();
	}

	public Pec3Impl(int port) throws RemoteException{
		super(port);
	}
	
	public String test() throws RemoteException{

		System.out.println("test()");
		return new String("Hello World with RMI!");
	}
	
}