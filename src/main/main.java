/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
import model.model;
import view.view;
import controller.controller;

/**
 *
 * @author Manuel
 */
public class main {
    public static void main(String[] args) throws Exception{
        model modelo = new model();
        view vista = new view();
        controller controlador = new controller(vista, modelo);
        controller.iniciarVista();
    }
}
