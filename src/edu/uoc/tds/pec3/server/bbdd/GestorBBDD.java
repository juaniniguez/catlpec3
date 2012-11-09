package edu.uoc.tds.pec3.server.bbdd;

import edu.uoc.tds.pec3.i18n.TDSLanguageUtils;
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
    
    public void cerrarBD(){
        try {
            conexion.close();
            conexionAbierta = false;
        } catch (SQLException ex) {
            //Logger.getLogger(GestorBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean conectaBD() {
        //Clase que hospeda la conexion
        try {
            //Recoger el driver JDBC especifico de Postgre
            Class.forName(driver);
            //Recuperar información del fichero de propiedades
            Properties prop = new Properties();
			System.out.println("Directorio actual:");
			System.out.println(System.getProperty("user.dir"));
            prop.load(new FileInputStream(propertiesFile));
            String url = prop.getProperty("url");
            String usuario = prop.getProperty("username");
            String clave = prop.getProperty("password");
            //Montar la conexion a la BBDD
            conexion = DriverManager.getConnection(url, usuario, clave);
            conexionAbierta = true;
            return true;
        } catch (ClassNotFoundException e) {
            //No se encontró el driver JDBC de Postgres en el classpath
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, TDSLanguageUtils.getMessage("jdbc.conexion.bd.error.driver.jdbc"), TDSLanguageUtils.getMessage("error"), 0);
        } catch (FileNotFoundException e) {
            //No se encontró el archivo "configuration.properties"
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, TDSLanguageUtils.getMessage("io.error.properties_no_encontrado"), TDSLanguageUtils.getMessage("error"), 0);
        } catch (IOException e) {
            //El fichero properties no es correcto
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, TDSLanguageUtils.getMessage("io.error.properties_no_correcto"), TDSLanguageUtils.getMessage("error"), 0);
        } catch (SQLException e) {
            //La base de datos no existe o login incorrecto
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, TDSLanguageUtils.getMessage("jdbc.conexion.bd.error.no_existe"), TDSLanguageUtils.getMessage("error"), 0);
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
            String query = "UPDATE RECURSO SET cantidad_estoc=?, fecha_ultima_entrada_estoc=? WHERE id_recurso=?";
            st = conexion.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            st.setInt(1, recurso.getStock());
            st.setDate(2, recurso.getUltimaEntrada());            
            st.setInt(3, recurso.getIdRecurso());
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
                JOptionPane.showMessageDialog(null, TDSLanguageUtils.getMessage("error.excepcion.noesperada"), TDSLanguageUtils.getMessage("error"), 0);
            }
        }
    }

    public List<Aula> recuperarAulas() {
        List<Aula> lista = new ArrayList<Aula>();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "SELECT id_aula, descripcion_aula FROM AULA";
            rs = st.executeQuery(query);
            while (rs.next()) {
                Aula aula = new Aula();
                aula.setIdAula(rs.getInt("id_aula"));
                aula.setDescripcion(rs.getString("descripcion_aula"));
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
            String query = "SELECT * FROM RECURSO";
            rs = st.executeQuery(query);
            while (rs.next()) {
                Recurso recurso = new Recurso();
                recurso.setIdRecurso(rs.getInt("id_recurso"));
                recurso.setNombreRecurso(rs.getString("nombre_recurso"));
                recurso.setStock(rs.getInt("id_recurso"));
                recurso.setUltimaEntrada(rs.getDate("fecha_ultima_entrada_estoc"));
                recurso.setUltimaSalida(rs.getDate("fecha_utlima_salida_estoc"));                
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
    
    public List<PeticionRecurso> recuperarPeticionesPendientes(boolean disponibles) {
        List<PeticionRecurso> lista = new ArrayList<PeticionRecurso>();
        Statement st = null;
        ResultSet rs = null;
        String noDisponibles=(disponibles ? "" : "NOT ");
        try {
            st = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "SELECT * FROM peticion_recurso p "+
                            "WHERE fecha_aceptacion is null AND "+noDisponibles+" EXISTS ("+
                            "SELECT cantidad_estoc FROM recurso r WHERE r.id_recurso=p.id_recurso "+
                            "AND r.cantidad_estoc>= p.cantidad)";
            rs = st.executeQuery(query);
            while (rs.next()) {
                PeticionRecurso peticion = new PeticionRecurso();
                peticion.setIdRecurso(rs.getInt("id_recurso"));
                peticion.setIdAula(rs.getInt("id_aula"));
                peticion.setFechaPeticion(rs.getDate("fecha_alta_peticion"));
                peticion.setFechaAceptacion(rs.getDate("fecha_aceptacion"));
                peticion.setCantidad(rs.getInt("cantidad"));
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
    
       public Recurso recuperarRecurso(int idRecurso) {
        Recurso rec = new Recurso();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "SELECT * FROM recurso "+
                            "WHERE id_recurso="+Integer.toString(idRecurso);
            rs = st.executeQuery(query);
            rec.setIdRecurso(rs.getInt("id_recurso"));
            rec.setNombreRecurso(rs.getString("nombre_recurso"));
            rec.setStock(rs.getInt("fecha_alta_peticion"));
            rec.setUltimaEntrada(rs.getDate("ultima_entrada_stock"));
            rec.setUltimaSalida(rs.getDate("ultima_salida_stock"));
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
        return rec;
    }
    
    public int recuperarNumPeticionesAulaPendientes(int idAula) {
        List<PeticionRecurso> lista = new ArrayList<PeticionRecurso>();
        Statement st = null;
        ResultSet rs = null;
        int ret=0;
        try {
            st = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "SELECT count(*) FROM peticion_recurso WHERE fecha_aceptacion is null AND id_aula="+Integer.toString(idAula)+" ORDER BY fecha_alta_peticion";
            rs = st.executeQuery(query);
            ret = rs.getInt(1);
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
        return ret;
    }
    
    public boolean modificarPeticion(PeticionRecurso peticion) {
        PreparedStatement st = null;
        try {
            String query = "UPDATE peticion_recurso SET cantidad=?, fecha_aceptacion=? WHERE id_recurso=? AND id_aula=? AND fecha_alta_peticion=?";
            st = conexion.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);            
            st.setInt(1, peticion.getCantidad());            
            st.setDate(2, peticion.getFechaAceptacion());
            st.setInt(3, peticion.getIdRecurso()); 
            st.setInt(4, peticion.getIdAula()); 
            st.setDate(5, peticion.getFechaPeticion());
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
    
    public boolean insertarPeticion(PeticionRecurso peticion) {
        PreparedStatement st = null;
        try {
            String query = "INSERT INTO peticion_recurso (id_recurso,id_aula,fecha_alta_peticion,cantidad) VALUES (?, ?, ?, ?)";
            st = conexion.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            st.setInt(1, peticion.getIdRecurso());
            st.setInt(2, peticion.getIdAula());
            st.setDate(3, peticion.getFechaPeticion());
            st.setInt(4, peticion.getCantidad());
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
    
}
