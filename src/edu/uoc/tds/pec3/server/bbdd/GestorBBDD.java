package edu.uoc.tds.pec3.server.bbdd;

import edu.uoc.tds.i18n.TDSLanguageUtils;
import edu.uoc.tds.pec3.beans.Aula;
import edu.uoc.tds.pec3.beans.PeticionRecurso;
import edu.uoc.tds.pec3.beans.Recurso;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.swing.JOptionPane;

public class GestorBBDD {

    public boolean conexionAbierta = false;
    
    private Connection conexion;    
    private final String propertiesFile = "properties/configuration.properties";
    private final String driver = "org.postgresql.Driver";
    

    public GestorBBDD() {
        super();
    }

    public boolean conectaBD() {
        //Clase que hospeda la conexion
        try {
            //Recoger el driver JDBC especifico de Postgre
            Class.forName(driver);
            //Recuperar información del fichero de propiedades
            Properties prop = new Properties();
            prop.load(new FileInputStream(propertiesFile));
            String url = prop.getProperty("url");
            String usuario = prop.getProperty("username");
            String clave = prop.getProperty("password");
            //Montar la conexion a la BBDD
            conexion = DriverManager.getConnection(url, usuario, clave);
            conexionAbierta = true;
            return true;
        } catch (ClassNotFoundException e) {
            //No se encuentra el driver JDBC de Postgre en el classpath
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, TDSLanguageUtils.getMessage("error.driver.jdbc"), TDSLanguageUtils.getMessage("error"), 0);
        } catch (FileNotFoundException e) {
            //No se encuentra el archivo "configuration.properties"
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, TDSLanguageUtils.getMessage("error.no.fichero.properties"), TDSLanguageUtils.getMessage("error"), 0);
        } catch (IOException e) {
            //El fichero de properties no es correcto
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, TDSLanguageUtils.getMessage("error.fichero.properties"), TDSLanguageUtils.getMessage("error"), 0);
        } catch (SQLException e) {
            //La base de dades no existe, está parada, o login incorrecto
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, TDSLanguageUtils.getMessage("error.bbdd.no.existe"), TDSLanguageUtils.getMessage("error"), 0);
        } catch (Exception e) {
            //Excepción inesperada
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, TDSLanguageUtils.getMessage("error.excepcion.inersperada"), TDSLanguageUtils.getMessage("error"), 0);
        }

        return false;
    }

    public boolean modificarRecurso(Recurso recurso) {
        PreparedStatement st = null;
        try {
            String query = "INSERT INTO votacion (id_entrenador,id_equipo,puntos,fecha_votacion) VALUES (?, ?, ?, ?)";
            st = conexion.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            /*
            st.setInt(1, recurso.getIdEntrenador());
            st.setInt(2, recurso.getIdEquipo());
            st.setInt(3, recurso.getPuntos());
            st.setDate(4, recurso.getFechaVotacion());
            * */
            st.execute();
            return true;
        } catch (SQLException e) {
            //La base de dades no existe, está parada, o login incorrecto
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, TDSLanguageUtils.getMessage("error.bbdd.no.existe"), TDSLanguageUtils.getMessage("error"), 0);
            return false;
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (Exception e) {
                //Excepción inesperada
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, TDSLanguageUtils.getMessage("error.excepcion.inersperada"), TDSLanguageUtils.getMessage("error"), 0);
            }
        }
    }

    public List<Aula> recuperarAulas() {
        List<Aula> lista = new ArrayList<Aula>();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "SELECT e.id_entrenador, e.id_equipo, e.nombre, e.apellidos, e.fecha_alta, eq.nombre AS nombre_equipo FROM entrenador AS e INNER JOIN equipo AS eq ON e.id_equipo = eq.id_equipo";
            rs = st.executeQuery(query);
            while (rs.next()) {
                Aula aula = new Aula();
                aula.setNombreRecurso(rs.getString("nombre_recurso"));
                lista.add(aula);
            }
        } catch (SQLException e) {
            //La base de dades no existe, está parada, o login incorrecto
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, TDSLanguageUtils.getMessage("error.bbdd.no.existe"), TDSLanguageUtils.getMessage("error"), 0);
        } finally {
            try {
                rs.close();
                st.close();
            } catch (Exception e) {
                //Excepción inesperada
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, TDSLanguageUtils.getMessage("error.excepcion.inersperada"), TDSLanguageUtils.getMessage("error"), 0);
            }
        }
        return lista;
    }

    public List<Recurso> recuperarRecursos() {
        List<Recurso> lista = new ArrayList<Recurso>();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "SELECT e.id_entrenador, e.id_equipo, e.nombre, e.apellidos, e.fecha_alta, eq.nombre AS nombre_equipo FROM entrenador AS e INNER JOIN equipo AS eq ON e.id_equipo = eq.id_equipo";
            rs = st.executeQuery(query);
            while (rs.next()) {
                Recurso recurso = new Recurso();
                recurso.setNombreRecurso(rs.getString("nombre_recurso"));
                lista.add(recurso);
            }
        } catch (SQLException e) {
            //La base de dades no existe, está parada, o login incorrecto
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, TDSLanguageUtils.getMessage("error.bbdd.no.existe"), TDSLanguageUtils.getMessage("error"), 0);
        } finally {
            try {
                rs.close();
                st.close();
            } catch (Exception e) {
                //Excepción inesperada
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, TDSLanguageUtils.getMessage("error.excepcion.inersperada"), TDSLanguageUtils.getMessage("error"), 0);
            }
        }
        return lista;
    }
    
    public List<PeticionRecurso> recuperarPeticionesPendientes() {
        List<PeticionRecurso> lista = new ArrayList<PeticionRecurso>();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "SELECT e.id_entrenador, e.id_equipo, e.nombre, e.apellidos, e.fecha_alta, eq.nombre AS nombre_equipo FROM entrenador AS e INNER JOIN equipo AS eq ON e.id_equipo = eq.id_equipo";
            rs = st.executeQuery(query);
            while (rs.next()) {
                PeticionRecurso peticion = new PeticionRecurso();
                //peticion.setNombreRecurso(rs.getString("nombre_recurso"));
                lista.add(peticion);
            }
        } catch (SQLException e) {
            //La base de dades no existe, está parada, o login incorrecto
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, TDSLanguageUtils.getMessage("error.bbdd.no.existe"), TDSLanguageUtils.getMessage("error"), 0);
        } finally {
            try {
                rs.close();
                st.close();
            } catch (Exception e) {
                //Excepción inesperada
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, TDSLanguageUtils.getMessage("error.excepcion.inersperada"), TDSLanguageUtils.getMessage("error"), 0);
            }
        }
        return lista;
    }
    
}
