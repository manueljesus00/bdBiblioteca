/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.SQLException;
import persistance.bibliotecaDAO;
import java.util.*;

/**
 *
 * @author Manuel
 */
public class model {
    bibliotecaDAO bDAO;

    public model() throws Exception {
        this.bDAO = new bibliotecaDAO();
    }
    
    public void nuevosPrestamos() throws SQLException{
        Scanner sc1 = new Scanner(System.in);
        System.out.println("Indique el nombre del libro: ");
        String libro = sc1.nextLine();
        Scanner sc2 = new Scanner(System.in);
        System.out.println("Indique el DNI del usuario: ");
        int dni = sc2.nextInt();
        bDAO.nuevosPrestamos(libro, dni);
        System.out.println("Prestamo registrado correctamente");
    }
    
    public void renovarPrestamos() throws SQLException{
        Scanner sc3 = new Scanner(System.in);
        System.out.println("Indique el DNI del usuario");
        int dni = sc3.nextInt();
        Scanner sc4 = new Scanner(System.in);
        System.out.println("Indique el numero de dias que quiere aumentar la renovacion");
        int numDias = sc4.nextInt();
        bDAO.renovarPrestamo(dni, numDias);
        System.out.println("Prestamo renovado correctamente");
    }
    
    public void buscarAutor() throws SQLException{
        Scanner sc5 = new Scanner(System.in);
        System.out.println("Introduce el nombre del autor");
        String autor = sc5.nextLine();
        ArrayList libros = bDAO.librosAutor(autor);
        if (libros != null){
            System.out.println("Titulo\tSignatura\t\n");
            for(int i = 0; i < libros.size(); i++){
                ArrayList registro = (ArrayList)libros.get(i);
                System.out.println(registro.get(0)+"\t"+registro.get(1)+"\t\n");
                
            }
        }else{
            System.out.println("No existe ningun libro de ese autor");
        }
    }
    
    public void consultarLibrosPrestados() throws SQLException{
        ArrayList libros = bDAO.librosPrestados();
        if (libros != null){
            System.out.println("Titulo\tDNI\tNombre usuario\t\n");
            for(int i = 0; i < libros.size(); i++){
                ArrayList registro = (ArrayList)libros.get(i);
                System.out.println(registro.get(0)+"\t"+registro.get(1)+"\t"+registro.get(2)+"\t\n");
            }
        }else{
            System.out.println("No existe ningun libro en prestamo");
        }
    }
    
    public void consultarLibrosPrestadosMasUnMes() throws SQLException{
        System.out.println("En mantenimiento...");
    }
}
