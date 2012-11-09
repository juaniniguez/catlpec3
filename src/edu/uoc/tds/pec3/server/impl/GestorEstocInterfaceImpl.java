package edu.uoc.tds.pec3.server.impl;

import edu.uoc.tds.pec3.beans.Aula;
import edu.uoc.tds.pec3.beans.PeticionRecurso;
import edu.uoc.tds.pec3.beans.Recurso;
import edu.uoc.tds.pec3.common.GestorEstocInterface;
import edu.uoc.tds.pec3.i18n.TDSLanguageUtils;
import edu.uoc.tds.pec3.server.bbdd.GestorBBDD;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import javax.swing.JOptionPane;

public class GestorEstocInterfaceImpl extends UnicastRemoteObject
        implements GestorEstocInterface, Serializable {

    private static final long serialVersionUID = 1L;
    private static final int maximoPeticionesPendientes = 3;
    private GestorBBDD gestorBBDD;

    public GestorEstocInterfaceImpl() throws RemoteException {
        super();

        conectaBD();
    }

    private void conectaBD() throws RemoteException {
        gestorBBDD = new GestorBBDD();
        if (!gestorBBDD.conectaBD()) {
            JOptionPane.showMessageDialog(null, TDSLanguageUtils.getMessage("jdbc.conexion.bd.error"), TDSLanguageUtils.getMessage("error"), 0);
        }else{
            JOptionPane.showMessageDialog(null, TDSLanguageUtils.getMessage("jdbc.conexion.bd.ok"), TDSLanguageUtils.getMessage("error"), 0);
        }
    }

    @Override
    public List<PeticionRecurso> obtenerPeticionesPendientes(boolean noDisponibles)  throws RemoteException{
        return gestorBBDD.recuperarPeticionesPendientes(noDisponibles);
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
    public void altaRecurso(Recurso recurso) throws RemoteException {
        gestorBBDD.modificarRecurso(recurso);
    }

    @Override
    public void altaPeticionRecurso(PeticionRecurso peticion) throws RemoteException {
        int numPeticiones=gestorBBDD.recuperarNumPeticionesAulaPendientes(peticion.getIdAula());
        if(numPeticiones<maximoPeticionesPendientes){
            gestorBBDD.insertarPeticion(peticion);            
        }else{
            //Devolver error de tope de peticiones
            JOptionPane.showMessageDialog(null, TDSLanguageUtils.getMessage("business.rules.error.demasiadas_peticiones"), TDSLanguageUtils.getMessage("error"), 0);            
        }
    }

    @Override
    public void aceptarPeticionRecurso(PeticionRecurso peticion) throws RemoteException {
        //Comprobar si el stock es suficiente
        Recurso rec = gestorBBDD.recuperarRecurso(peticion.getIdRecurso());
        if(rec.getStock()>=peticion.getCantidad()){
            //Establecer fecha y grabar la petición
            gestorBBDD.modificarPeticion(peticion);
            //Establecer fecha ultima salida y nuevo stock en recurso
            rec.setStock(rec.getStock()-peticion.getCantidad());
            //rec.setUltimaSalida(new Date());
            gestorBBDD.modificarRecurso(rec);
        }else{
            //Elevar excepcion de stock insuficiente
            JOptionPane.showMessageDialog(null, TDSLanguageUtils.getMessage("business.rules.error.stock_insuficiente"), TDSLanguageUtils.getMessage("error"), 0);
        }
    }
    
    @Override
    public String test() throws RemoteException {
        return "Test RMI GestorEstoc OK";
    }

}
