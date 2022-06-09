/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import model.model;
import view.view;

/**
 *
 * @author Manuel
 */
public class controller {
    private view vista;
    private model modelo;
    public controller(view vista, model modelo){
        this.vista = vista;
        this.modelo = modelo;
    }
    public void iniciarVista(){
        vista.imprimirMenu();
    }
    
}
