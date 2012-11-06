package edu.uoc.tds.pec3.beans;

import java.io.Serializable;
import java.sql.Date;

public class Recurso implements Serializable {

    private String nombreRecurso;

    public String getNombreRecurso() {
        return nombreRecurso;
    }

    public void setNombreRecurso(String nombreRecurso) {
        this.nombreRecurso = nombreRecurso;
    }
    
}