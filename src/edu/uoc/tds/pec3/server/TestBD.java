package edu.uoc.tds.pec3.server;

import edu.uoc.tds.pec3.server.bbdd.GestorBBDD;
import java.io.IOException;

public class TestBD {
    public static void main(String args[]) throws IOException{
        GestorBBDD gestor = new GestorBBDD();
        boolean conectado = gestor.conectaBD();
        System.out.println("Conexion a la base de datos: "+conectado);
    }
}
