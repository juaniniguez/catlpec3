package edu.uoc.tds.pec3.server.impl;

import edu.uoc.tds.pec3.beans.Aula;
import edu.uoc.tds.pec3.beans.PeticionRecurso;
import edu.uoc.tds.pec3.beans.Recurso;
import edu.uoc.tds.pec3.common.GestorEstocInterface;
import edu.uoc.tds.pec3.server.bbdd.GestorBBDD;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.List;

public class GestorEstocInterfaceImpl extends UnicastRemoteObject
        implements GestorEstocInterface, Serializable {

    private static final long serialVersionUID = 1L;
    private GestorBBDD gestorBBDD;

    public GestorEstocInterfaceImpl() throws RemoteException {
        super();

        conectaBD();
    }

    private void conectaBD() throws RemoteException {
        gestorBBDD = new GestorBBDD();
        if (!gestorBBDD.conectaBD()) {
            System.out.print("Error conexion BD");
            //JOptionPane.showMessageDialog(null, TDSLanguageUtils.getMessage("error.conexion.bd"), TDSLanguageUtils.getMessage("error"), 0);
        }else{
            System.out.println("Conexion correcta a la base de datos");
        }
    }

    @Override
    public List<PeticionRecurso> obtenerPeticionesPendientes()  throws RemoteException{
        return gestorBBDD.recuperarPeticionesPendientes();
    }


    @Override
    public List<Aula> obtenerAulas() throws RemoteException {
        return gestorBBDD.recuperarAulas() ;
    }

    @Override
    public List<Recurso> obtenerRecursos() throws RemoteException {
        return gestorBBDD.recuperarRecursos();
    }

    @Override
    public void altaRecurso(Integer idRecurso, Integer cantidad) throws RemoteException {
        //gestorBBDD.modificarRecurso(Recurso);
    }

    @Override
    public void altaPeticionRecurso(Integer idRecurso, Integer idAula, Integer cantidad) throws RemoteException {
        //gestorBBDD.modificaPeticionRecurso(PeticionRecurso);
    }

    @Override
    public void aceptarPeticionRecurso(Integer idRecurso, Integer idAula, Date fechaAltaPeticion) throws RemoteException {
        //gestorBBDD.modificaPeticionRecurso(PeticionRecurso);
    }
    
    @Override
    public String test() throws RemoteException {
        return "GestorEstoc OK";
    }

}
