package edu.uoc.tds.pec3.beans;

import java.io.Serializable;
import java.sql.Date;

public class Recurso implements Serializable {

    private int idRecurso;
    private String nombreRecurso;
    private int stock;
    private Date ultimaEntrada;
    private Date ultimaSalida;

    public int getIdRecurso() {
        return idRecurso;
    }
    
    public void setIdRecurso(int idRecurso) {
        this.idRecurso = idRecurso;
    }

    public String getNombreRecurso() {
        return nombreRecurso;
    }

    public void setNombreRecurso(String nombreRecurso) {
        this.nombreRecurso = nombreRecurso;
    }
    
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Date getUltimaEntrada() {
        return ultimaEntrada;
    }

    public void setUltimaEntrada(Date ultimaEntrada) {
        this.ultimaEntrada = ultimaEntrada;
    }

    public Date getUltimaSalida() {
        return ultimaSalida;
    }

    public void setUltimaSalida(Date ultimaSalida) {
        this.ultimaSalida = ultimaSalida;
    }
    
}