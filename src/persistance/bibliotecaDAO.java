/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistance;
import java.util.Date;
import java.sql.*;
import java.util.*;


/**
 *
 * @author Manuel
 */
public class bibliotecaDAO {
    private persistencia p;
    private final Connection c;
    

    public bibliotecaDAO() throws Exception {
        this.c = p.createConnection();
    }
    
    // Consulta libros de un autor determinado
    public ArrayList librosAutor (String nom) throws SQLException{
        // Lo hago con ArrayList para que modelo los muestre
        ArrayList devolverLibros = new ArrayList();
        String sentencia = "SELECT * FROM libro WHERE nombre = ?";
        PreparedStatement librosAutor = c.prepareStatement(sentencia);
        librosAutor.setString(0, nom);
        ResultSet res = librosAutor.executeQuery();
        while(res.next()){
            ArrayList libro = new ArrayList();
            libro.add(res.getString("titulo"));
            libro.add(res.getString("signatura"));
            devolverLibros.add(libro);
        }
        librosAutor.close();
        res.close();
        return devolverLibros;
    }
    
    // Consulta libros prestados (valido para mas de un mes tambien, las operaciones se hacen en el modelo)
    // Return arraylist with arraylists that contains book title, user dni and user name.
    public ArrayList librosPrestados() throws SQLException{
        ArrayList librosEnPrestamo = new ArrayList();
        String sentencia = "SELECT libro.titulo, usuario.dni, usuario.nombre FROM prestamo INNER JOIN libro ON prestamo.idLibro = libro.idLibro INNER JOIN usuario ON prestamo.idUsuario = usuario.idUsuario";
        Statement stmt = c.createStatement();
        ResultSet res = stmt.executeQuery(sentencia);
        while(res.next()){
            ArrayList registro = new ArrayList();
            registro.add(res.getString("titulo"));
            registro.add(res.getInt("dni"));
            registro.add(res.getString("nombre"));
            librosEnPrestamo.add(registro);
        }
        return librosEnPrestamo;
    }
    
    // Introducir nuevos prestamos
    public void nuevosPrestamos(String libro, int DNI) throws SQLException{
        try{
            String insert = "INSERT INTO prestamo (idLibro, idUsuario, fechaPrestamo, fechaDevolucion) VALUES (?, ?, ?, ?)";
            PreparedStatement pStmt = c.prepareStatement(insert);
            pStmt.setInt(0, identificarLibro(libro));
            pStmt.setInt(1, identificarUsuario(DNI));
            java.sql.Date fechaInicio = new java.sql.Date(System.currentTimeMillis());
            pStmt.setDate(2, fechaInicio);
            //1296000000 son 15 dias en milisegundos
            java.sql.Date fechaFinal = new java.sql.Date((int)fechaInicio.getTime() + 1296000000);
            pStmt.setDate(3, fechaFinal);
            pStmt.execute();
        }catch (SQLException ex){
            System.out.println(ex);
        }
    }
    
    // Renovar prestamo
    public void renovarPrestamo(int dni, int numDias) throws SQLException{
        try{
            String actualizarPrestamo = "UPDATE prestamo SET fechaDevolucion = ? WHERE idPrestamo = ?";
            PreparedStatement pStmt = c.prepareStatement(actualizarPrestamo);
            java.sql.Date fecha = devolverFechaDevolucion(identificarPrestamo(dni));
            java.sql.Date fechaNuevaFinal = new java.sql.Date((int)fecha.getTime() + (numDias * 86400000));
            pStmt.setDate(0, fechaNuevaFinal);
            pStmt.setInt(1, identificarPrestamo(dni));
            pStmt.execute();
            
        }catch (SQLException ex){
            System.out.println(ex);
        }
    }
    
    // Metodo auxiliar para buscar usuarios
    private int identificarUsuario(int documento) throws SQLException{
        String read = "SELECT idUsuario FROM usuario WHERE dni = ?";
        PreparedStatement pStmt = c.prepareStatement(read);
        pStmt.setInt(0, documento);
        ResultSet rs = pStmt.executeQuery();
        return rs.getInt("idUsuario");
    }
    
    // Metodo auxiliar para buscar libros
    private int identificarLibro(String libro) throws SQLException{
        String read = "SELECT idLibro FROM libro WHERE titulo = ?";
        PreparedStatement pStmt = c.prepareStatement(read);
        pStmt.setString(0, libro);
        ResultSet rs = pStmt.executeQuery();
        return rs.getInt("idLibro");
    }
    
    // Metodo auxiliar para buscar un prestamo en base al DNI
    private int identificarPrestamo(int dni) throws SQLException{
        String read = "SELECT idPrestamo FROM prestamo WHERE idUsuario = ? ORDER BY idPrestamo DESC LIMIT 1";
        PreparedStatement pStmt = c.prepareStatement(read);
        pStmt.setInt(0, identificarUsuario(dni));
        ResultSet rs = pStmt.executeQuery();
        return rs.getInt("idPrestamo");
    }
    
    // Metodo auxiliar para obtener la fecha de devolucion de un prestamo
    private java.sql.Date devolverFechaDevolucion(int idPrestamo) throws SQLException{
        String read = "SELECT fechaDevolucion FROM prestamo WHERE idPrestamo = ?";
        PreparedStatement pStmt = c.prepareStatement(read);
        pStmt.setInt(0, idPrestamo);
        ResultSet rs = pStmt.executeQuery();
        return rs.getDate("fechaDevolucion");
    }

}
