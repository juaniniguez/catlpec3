package edu.uoc.tds.pec3.beans;

import java.io.Serializable;
import java.sql.Date;

public class Aula implements Serializable {

    private int idAula;
    private String descripcion;

    public int getIdAula() {
        return idAula;
    }

    public void setIdAula(int idAula) {
        this.idAula = idAula;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}