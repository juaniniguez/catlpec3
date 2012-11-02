/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uoc.tds.pec3.common;
/* EJEMPLO CONEXION RMI PARA TDS/TDP
* Universitat Oberta de Catalunya (UOC) - Primavera de 2012
* Joan Esteve Riasol
*/

import java.rmi.RemoteException;

/**
* Interficie RMI. Debe extender de la clase java.rmi.Remote
* Esta clase debe ser visible tanto para cliente como para servidor
* @author jestever@uoc.edu
*/

public interface Pec3Interface extends java.rmi.Remote{

/**
* Metodo sencillo de test de la conexion RMI
* @return Un String con un mensaje de bienvenida
* @throws RemoteException
*/

public String test() throws RemoteException;

}
