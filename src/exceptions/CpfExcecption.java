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
public class CpfExcecption extends Exception{
    public CpfExcecption(String mensagem){    
        super("Preencha o CPF");
    }
}
