package edu.uoc.tds.pec3.beans;

import java.io.Serializable;
import java.sql.Date;

public class Aula implements Serializable {

    private int idRecurso;
    private String nombreRecurso;
    private int stock;
    private Date ultimaEntradaStock;
    private Date ultimaSalidaStock;    

    public int getIdRecurso() {
        return idRecurso;
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

    public Date getUltimaEntradaStock() {
        return ultimaEntradaStock;
    }

    public void setUltimaEntradaStock(Date ultimaEntradaStock) {
        this.ultimaEntradaStock = ultimaEntradaStock;
    }

    public Date getUltimaSalidaStock() {
        return ultimaSalidaStock;
    }

    public void setUltimaSalidaStock(Date ultimaSalidaStock) {
        this.ultimaSalidaStock = ultimaSalidaStock;
    }

}