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
import uoc.tds.pec3.common.GestorEstocInterface;

/**
* Clase implementacion de la interficie RMI. Capa de negocio.
* Esta clase solo es de la parte servidor.
* @author jestever@uoc.edu
*/

public class GestorEstocImpl extends java.rmi.server.UnicastRemoteObject implements GestorEstocInterface,Serializable{

	private static final long serialVersionUID = 1L;
	
	public GestorEstocImpl() throws RemoteException {
		super();
	}

	public GestorEstocImpl(int port) throws RemoteException{
		super(port);
	}
	
	public String test() throws RemoteException{

		System.out.println("test()");
		return new String("Hello World with RMI!");
	}
	
}