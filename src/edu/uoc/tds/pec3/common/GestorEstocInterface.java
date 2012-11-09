package edu.uoc.tds.pec3.common;

import edu.uoc.tds.pec3.beans.Aula;
import edu.uoc.tds.pec3.beans.PeticionRecurso;
import edu.uoc.tds.pec3.beans.Recurso;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

/**
 * Interfaz RMI. Debe extender de la clase java.rmi.Remote
 * Esta clase debe ser visible tanto para cliente como para servidor
 * @author Gabriel Hurtado, Juan M.I�iguez
 */
public interface GestorEstocInterface extends Remote {

    public List<Aula> obtenerAulas() throws RemoteException;

<<<<<<< HEAD
    public List<PeticionRecurso> obtenerPeticionesPendientes(boolean noDisponibles) throws RemoteException;

    public List<Recurso> obtenerRecursos() throws RemoteException;

    public void altaRecurso(Recurso recurso) throws RemoteException;

    public void altaPeticionRecurso(PeticionRecurso peticion) throws RemoteException;

    public void aceptarPeticionRecurso(PeticionRecurso peticion) throws RemoteException;
=======
    public List<PeticionRecurso> obtenerPeticionesPendientes() throws RemoteException;

    public List<Recurso> obtenerRecursos() throws RemoteException;

    public void altaRecurso(Integer idRecurso,  Integer cantidad) throws RemoteException;

    public void altaPeticionRecurso(Integer idRecurso,  Integer idAula, Integer cantidad) throws RemoteException;

    public void aceptarPeticionRecurso(Integer idRecurso,  Integer idAula, Date fechaAltaPeticion) throws RemoteException;
>>>>>>> branch 'master' of https://github.com/juaniniguez/catlpec3.git

    public String test() throws RemoteException;
    
}

