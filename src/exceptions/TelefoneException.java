/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Diones Gomes
 */
public class TelefoneException extends Exception{
    public TelefoneException(String mensagem){    
        super("Preencha o Telefone");
    }
}   
