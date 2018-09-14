/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.github.lanchonete.exceptions;

/**
 * Esta classe é responsável por a execção de formulário vazio.
 * @author Diones Gomes
 */
public class FormularioException extends Exception {  
    
    public FormularioException(String mensagem){    
        super(mensagem);
    }
}
